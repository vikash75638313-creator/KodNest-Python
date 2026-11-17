package com.example.loginapp.ui.screen

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginapp.viewmodel.AuthState
import com.example.loginapp.viewmodel.AuthViewModel

@Composable
fun SignUpScreen(
    onSignUpSuccess: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {},
    authViewModel: AuthViewModel = viewModel()
) {
    val authState by authViewModel.authState.collectAsState()

    var displayName     by remember { mutableStateOf("") }
    var email           by remember { mutableStateOf("") }
    var password        by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showPassword    by remember { mutableStateOf(false) }
    var showConfirm     by remember { mutableStateOf(false) }

    var nameError    by remember { mutableStateOf("") }
    var emailError   by remember { mutableStateOf("") }
    var passError    by remember { mutableStateOf("") }
    var confirmError by remember { mutableStateOf("") }

    // Navigate on success
    LaunchedEffect(authState) {
        if (authState is AuthState.Success) onSignUpSuccess()
    }

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
                Text("Create Account ✨", fontSize = 26.sp,
                    fontWeight = FontWeight.Bold, color = Color(0xFF6650A4))
                Spacer(modifier = Modifier.height(4.dp))
                Text("Join us today", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(24.dp))

                // Display Name
                OutlinedTextField(
                    value = displayName,
                    onValueChange = { displayName = it; nameError = "" },
                    label = { Text("Full Name") },
                    leadingIcon = { Icon(Icons.Default.Person, null, tint = Color(0xFF6650A4)) },
                    isError = nameError.isNotEmpty(),
                    supportingText = {
                        if (nameError.isNotEmpty()) Text(nameError, color = MaterialTheme.colorScheme.error)
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

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

                Spacer(modifier = Modifier.height(10.dp))

                // Confirm Password
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it; confirmError = "" },
                    label = { Text("Confirm Password") },
                    leadingIcon = { Icon(Icons.Default.LockReset, null, tint = Color(0xFF6650A4)) },
                    trailingIcon = {
                        IconButton(onClick = { showConfirm = !showConfirm }) {
                            Icon(
                                imageVector = if (showConfirm) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = null, tint = Color.Gray
                            )
                        }
                    },
                    visualTransformation = if (showConfirm) VisualTransformation.None
                                           else PasswordVisualTransformation(),
                    isError = confirmError.isNotEmpty(),
                    supportingText = {
                        if (confirmError.isNotEmpty()) Text(confirmError, color = MaterialTheme.colorScheme.error)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                // Auth error
                if (authState is AuthState.Error) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = (authState as AuthState.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Sign Up Button
                Button(
                    onClick = {
                        var valid = true
                        if (displayName.isBlank()) { nameError = "Name is required"; valid = false }
                        if (email.isBlank()) { emailError = "Email is required"; valid = false }
                        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            emailError = "Enter a valid email"; valid = false
                        }
                        if (password.isBlank()) { passError = "Password is required"; valid = false }
                        else if (password.length < 6) { passError = "Min 6 characters required"; valid = false }
                        if (confirmPassword != password) { confirmError = "Passwords don't match"; valid = false }
                        if (valid) authViewModel.signUp(email, password, displayName)
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
                        Text("Create Account", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Back to login
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Already have an account?", fontSize = 13.sp, color = Color.Gray)
                    TextButton(onClick = {
                        authViewModel.resetState()
                        onNavigateToLogin()
                    }) {
                        Text("Sign In", color = Color(0xFF6650A4),
                            fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}

