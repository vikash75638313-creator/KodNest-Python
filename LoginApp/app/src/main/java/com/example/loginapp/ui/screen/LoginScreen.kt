package com.example.loginapp.ui.screen

import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginapp.viewmodel.AuthState
import com.example.loginapp.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {},
    onNavigateToSignUp: () -> Unit = {},
    onNavigateToForgotPassword: () -> Unit = {},
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current

    val authState by authViewModel.authState.collectAsState()
    val savedEmail by authViewModel.savedEmail.collectAsState()
    val rememberMeSaved by authViewModel.rememberMe.collectAsState()
    val biometricEnabled by authViewModel.biometricEnabled.collectAsState()

    var email       by remember { mutableStateOf("") }
    var password    by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var rememberMe  by remember { mutableStateOf(false) }
    var emailError  by remember { mutableStateOf("") }
    var passError   by remember { mutableStateOf("") }

    // Pre-fill email if Remember Me was checked last time
    LaunchedEffect(savedEmail, rememberMeSaved) {
        if (rememberMeSaved && savedEmail.isNotBlank()) {
            email = savedEmail
            rememberMe = true
        }
    }

    // Handle auth state changes
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Success -> onLoginSuccess()
            is AuthState.Error   -> { /* shown inline */ }
            else -> Unit
        }
    }

    // ── Biometric helper ────────────────────────────────────────────────────
    fun launchBiometric() {
        val activity = context as? FragmentActivity ?: return
        val executor = ContextCompat.getMainExecutor(context)
        val prompt = BiometricPrompt(
            activity, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    // Biometric OK → trigger Firebase login with saved credentials
                    // In a real app you'd store credentials in EncryptedSharedPreferences
                    Toast.makeText(context, "Biometric verified! Signing in…", Toast.LENGTH_SHORT).show()
                    authViewModel.login(savedEmail, "##biometric##", rememberMe)
                }
                override fun onAuthenticationError(code: Int, msg: CharSequence) {
                    Toast.makeText(context, "Biometric error: $msg", Toast.LENGTH_SHORT).show()
                }
                override fun onAuthenticationFailed() {
                    Toast.makeText(context, "Biometric not recognised", Toast.LENGTH_SHORT).show()
                }
            }
        )
        val info = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Sign in with Biometric")
            .setSubtitle("Use your fingerprint or face to log in")
            .setNegativeButtonText("Use Password")
            .build()
        prompt.authenticate(info)
    }

    // ── Biometric availability check ────────────────────────────────────────
    val canUseBiometric = remember {
        BiometricManager.from(context)
            .canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK) ==
                BiometricManager.BIOMETRIC_SUCCESS
    }

    // ── UI ──────────────────────────────────────────────────────────────────
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF6650A4), Color(0xFF9C89B8))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp, vertical = 36.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Title
                Text("Welcome Back 👋", fontSize = 26.sp, fontWeight = FontWeight.Bold,
                    color = Color(0xFF6650A4))
                Spacer(modifier = Modifier.height(4.dp))
                Text("Sign in to continue", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(28.dp))

                // Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it; emailError = "" },
                    label = { Text("Email") },
                    leadingIcon = { Icon(Icons.Default.Email, null, tint = Color(0xFF6650A4)) },
                    isError = emailError.isNotEmpty(),
                    supportingText = {
                        if (emailError.isNotEmpty()) Text(emailError, color = MaterialTheme.colorScheme.error)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Password
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it; passError = "" },
                    label = { Text("Password") },
                    leadingIcon = { Icon(Icons.Default.Lock, null, tint = Color(0xFF6650A4)) },
                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            Icon(
                                imageVector = if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = null, tint = Color.Gray
                            )
                        }
                    },
                    visualTransformation = if (showPassword) VisualTransformation.None
                                           else PasswordVisualTransformation(),
                    isError = passError.isNotEmpty(),
                    supportingText = {
                        if (passError.isNotEmpty()) Text(passError, color = MaterialTheme.colorScheme.error)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                // Remember Me row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = rememberMe,
                            onCheckedChange = { rememberMe = it },
                            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF6650A4))
                        )
                        Text("Remember me", fontSize = 13.sp, color = Color.DarkGray)
                    }
                    TextButton(onClick = onNavigateToForgotPassword) {
                        Text("Forgot Password?", color = Color(0xFF6650A4), fontSize = 12.sp)
                    }
                }

                // Auth error
                if (authState is AuthState.Error) {
                    Text(
                        text = (authState as AuthState.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Login Button
                Button(
                    onClick = {
                        var valid = true
                        if (email.isBlank()) { emailError = "Email is required"; valid = false }
                        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            emailError = "Enter a valid email"; valid = false
                        }
                        if (password.isBlank()) { passError = "Password is required"; valid = false }
                        else if (password.length < 6) { passError = "Min 6 characters required"; valid = false }
                        if (valid) authViewModel.login(email, password, rememberMe)
                    },
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6650A4)),
                    enabled = authState !is AuthState.Loading
                ) {
                    if (authState is AuthState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.size(22.dp),
                            color = Color.White, strokeWidth = 2.dp)
                    } else {
                        Text("Login", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    }
                }

                // Biometric button (shown only if enrolled & user previously enabled it)
                if (canUseBiometric && biometricEnabled && savedEmail.isNotBlank()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedButton(
                        onClick = { launchBiometric() },
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.5.dp)
                    ) {
                        Icon(Icons.Default.Fingerprint, null,
                            modifier = Modifier.size(20.dp), tint = Color(0xFF6650A4))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Sign in with Biometric", color = Color(0xFF6650A4), fontSize = 14.sp)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // ── Divider ───────────────────────────────────
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray)
                    Text("  or  ", fontSize = 12.sp, color = Color.Gray)
                    HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // ── Google Sign-In button ─────────────────────
                OutlinedButton(
                    onClick = { authViewModel.signInWithGoogle(context) },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
                    enabled = authState !is AuthState.Loading
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_search),
                        contentDescription = "Google",
                        modifier = Modifier.size(20.dp),
                        tint = Color(0xFF4285F4)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Continue with Google", color = Color.DarkGray, fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sign-up link
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Don't have an account?", fontSize = 13.sp, color = Color.Gray)
                    TextButton(onClick = onNavigateToSignUp) {
                        Text("Sign Up", color = Color(0xFF6650A4),
                            fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}
