package com.example.loginapp.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension for DataStore
val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        val REMEMBER_ME     = booleanPreferencesKey("remember_me")
        val SAVED_EMAIL     = stringPreferencesKey("saved_email")
        val BIOMETRIC_ENABLED = booleanPreferencesKey("biometric_enabled")
    }

    val rememberMe: Flow<Boolean> = context.dataStore.data
        .map { it[REMEMBER_ME] ?: false }

    val savedEmail: Flow<String> = context.dataStore.data
        .map { it[SAVED_EMAIL] ?: "" }

    val biometricEnabled: Flow<Boolean> = context.dataStore.data
        .map { it[BIOMETRIC_ENABLED] ?: false }

    suspend fun saveRememberMe(rememberMe: Boolean, email: String) {
        context.dataStore.edit { prefs ->
            prefs[REMEMBER_ME] = rememberMe
            prefs[SAVED_EMAIL] = if (rememberMe) email else ""
        }
    }

    suspend fun setBiometricEnabled(enabled: Boolean) {
        context.dataStore.edit { it[BIOMETRIC_ENABLED] = enabled }
    }

    suspend fun clearAll() {
        context.dataStore.edit { it.clear() }
    }
}

