package com.example.loginapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapp.data.UserPreferences
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

// ── UI State ───────────────────────────────────────────────────────────────────
sealed class AuthState {
    object Idle    : AuthState()
    object Loading : AuthState()
    data class Success(val user: FirebaseUser) : AuthState()
    data class Error(val message: String)      : AuthState()
    object LoggedOut : AuthState()
    data class PasswordResetSent(val email: String) : AuthState()
}

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val auth  = FirebaseAuth.getInstance()
    private val prefs = UserPreferences(application)

    // ── IMPORTANT: Replace with your Web Client ID from Firebase Console ──────
    // Firebase Console → Project Settings → Your apps → Web API key
    private val WEB_CLIENT_ID = "YOUR_WEB_CLIENT_ID_HERE"

    // ── State ─────────────────────────────────────────────────────────────────
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    val currentUser: FirebaseUser? get() = auth.currentUser

    val savedEmail: StateFlow<String> = prefs.savedEmail
        .stateIn(viewModelScope, SharingStarted.Eagerly, "")

    val rememberMe: StateFlow<Boolean> = prefs.rememberMe
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val biometricEnabled: StateFlow<Boolean> = prefs.biometricEnabled
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    // ── Email / Password Login ────────────────────────────────────────────────
    fun login(email: String, password: String, rememberMe: Boolean) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                result.user?.let { user ->
                    prefs.saveRememberMe(rememberMe, email)
                    _authState.value = AuthState.Success(user)
                } ?: run { _authState.value = AuthState.Error("Login failed. Try again.") }
            } catch (e: Exception) {
                _authState.value = AuthState.Error(friendlyError(e.message))
            }
        }
    }

    // ── Sign Up ───────────────────────────────────────────────────────────────
    fun signUp(email: String, password: String, displayName: String) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                result.user?.let { user ->
                    val profileUpdate = com.google.firebase.auth.userProfileChangeRequest {
                        this.displayName = displayName
                    }
                    user.updateProfile(profileUpdate).await()
                    _authState.value = AuthState.Success(user)
                } ?: run { _authState.value = AuthState.Error("Sign up failed.") }
            } catch (e: Exception) {
                _authState.value = AuthState.Error(friendlyError(e.message))
            }
        }
    }

    // ── Google Sign-In (Credential Manager) ───────────────────────────────────
    fun signInWithGoogle(context: Context) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                val credentialManager = CredentialManager.create(context)

                val googleIdOption = GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(WEB_CLIENT_ID)
                    .setAutoSelectEnabled(true)
                    .build()

                val request = GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build()

                val credentialResponse = credentialManager.getCredential(context, request)
                val googleIdToken = GoogleIdTokenCredential
                    .createFrom(credentialResponse.credential.data)
                    .idToken

                val firebaseCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
                val result = auth.signInWithCredential(firebaseCredential).await()

                result.user?.let { user ->
                    _authState.value = AuthState.Success(user)
                } ?: run { _authState.value = AuthState.Error("Google sign-in failed.") }

            } catch (e: GetCredentialException) {
                _authState.value = AuthState.Error("Google sign-in cancelled or unavailable.")
            } catch (e: Exception) {
                _authState.value = AuthState.Error(friendlyError(e.message))
            }
        }
    }

    // ── Forgot Password ───────────────────────────────────────────────────────
    fun sendPasswordResetEmail(email: String) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                auth.sendPasswordResetEmail(email).await()
                _authState.value = AuthState.PasswordResetSent(email)
            } catch (e: Exception) {
                _authState.value = AuthState.Error(friendlyError(e.message))
            }
        }
    }

    // ── Biometric ─────────────────────────────────────────────────────────────
    fun setBiometricEnabled(enabled: Boolean) {
        viewModelScope.launch { prefs.setBiometricEnabled(enabled) }
    }

    // ── Logout ────────────────────────────────────────────────────────────────
    fun logout() {
        viewModelScope.launch {
            auth.signOut()
            prefs.clearAll()
            _authState.value = AuthState.LoggedOut
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────────────
    fun resetState() { _authState.value = AuthState.Idle }

    private fun friendlyError(raw: String?): String {
        return when {
            raw == null -> "An unexpected error occurred."
            raw.contains("no user record")        -> "No account found with this email."
            raw.contains("password is invalid")   -> "Incorrect password. Please try again."
            raw.contains("email address is already") -> "This email is already registered."
            raw.contains("network")               -> "No internet connection. Check your network."
            raw.contains("INVALID_LOGIN_CREDENTIALS") -> "Invalid email or password."
            else -> raw.replace("com.google.firebase.auth.", "")
        }
    }
}
