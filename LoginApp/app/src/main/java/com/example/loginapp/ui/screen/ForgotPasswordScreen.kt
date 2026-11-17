package com.example.loginapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MarkEmailRead
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginapp.viewmodel.AuthState
import com.example.loginapp.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    onNavigateBack: () -> Unit = {},
    authViewModel: AuthViewModel = viewModel()
) {
    val authState by authViewModel.authState.collectAsState()
    var email     by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }

    // Reset state when entering screen
    LaunchedEffect(Unit) { authViewModel.resetState() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reset Password") },
                navigationIcon = {
                    IconButton(onClick = {
                        authViewModel.resetState()
                        onNavigateBack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6650A4),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF6650A4), Color(0xFF9C89B8))
                    )
                )
                .padding(padding),
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
                    // ── Success state ───────────────────────────────────────
                    if (authState is AuthState.PasswordResetSent) {
                        val sentEmail = (authState as AuthState.PasswordResetSent).email

                        Icon(
                            Icons.Default.MarkEmailRead, null,
                            modifier = Modifier.size(72.dp),
                            tint = Color(0xFF6650A4)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Email Sent! 📬", fontSize = 22.sp,
                            fontWeight = FontWeight.Bold, color = Color(0xFF6650A4))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "We've sent a password reset link to:\n$sentEmail\n\nCheck your inbox (and spam folder).",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(28.dp))
                        Button(
                            onClick = {
                                authViewModel.resetState()
                                onNavigateBack()
                            },
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6650A4))
                        ) {
                            Text("Back to Login", fontSize = 15.sp)
                        }

                    } else {
                        // ── Input state ─────────────────────────────────────
                        Icon(
                            Icons.Default.Email, null,
                            modifier = Modifier.size(56.dp),
                            tint = Color(0xFF6650A4)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Forgot Password?", fontSize = 22.sp,
                            fontWeight = FontWeight.Bold, color = Color(0xFF6650A4))
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            "Enter your registered email address.\nWe'll send you a reset link.",
                            fontSize = 13.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it; emailError = "" },
                            label = { Text("Email Address") },
                            leadingIcon = {
                                Icon(Icons.Default.Email, null, tint = Color(0xFF6650A4))
                            },
                            isError = emailError.isNotEmpty(),
                            supportingText = {
                                if (emailError.isNotEmpty())
                                    Text(emailError, color = MaterialTheme.colorScheme.error)
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        // Auth error
                        if (authState is AuthState.Error) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = (authState as AuthState.Error).message,
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 13.sp,
                                textAlign = TextAlign.Center
                            )
                        }

                        Spacer(modifier = Modifier.height(22.dp))

                        Button(
                            onClick = {
                                if (email.isBlank()) {
                                    emailError = "Email is required"
                                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                    emailError = "Enter a valid email"
                                } else {
                                    authViewModel.sendPasswordResetEmail(email.trim())
                                }
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
                                Text("Send Reset Link", fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold)
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))
                        TextButton(onClick = {
                            authViewModel.resetState()
                            onNavigateBack()
                        }) {
                            Text("← Back to Login", color = Color(0xFF6650A4), fontSize = 13.sp)
                        }
                    }
                }
            }
        }
    }
}

