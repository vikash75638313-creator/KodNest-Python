package com.example.loginapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginapp.ui.screen.ForgotPasswordScreen
import com.example.loginapp.ui.screen.HomeScreen
import com.example.loginapp.ui.screen.LoginScreen
import com.example.loginapp.ui.screen.SignUpScreen
import com.example.loginapp.ui.screen.SplashScreen
import com.example.loginapp.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // Single shared ViewModel across all screens
    val authViewModel: AuthViewModel = viewModel()

    NavHost(navController = navController, startDestination = "splash") {

        // ── Splash ──────────────────────────────────────────────────────────
        composable("splash") {
            SplashScreen(
                onSplashFinished = {
                    val destination = if (FirebaseAuth.getInstance().currentUser != null) "home"
                                     else "login"
                    navController.navigate(destination) {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        // ── Login ────────────────────────────────────────────────────────────
        composable("login") {
            LoginScreen(
                authViewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToSignUp = {
                    authViewModel.resetState()
                    navController.navigate("signup")
                },
                onNavigateToForgotPassword = {
                    authViewModel.resetState()
                    navController.navigate("forgot_password")
                }
            )
        }

        // ── Sign Up ──────────────────────────────────────────────────────────
        composable("signup") {
            SignUpScreen(
                authViewModel = authViewModel,
                onSignUpSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        // ── Forgot Password ──────────────────────────────────────────────────
        composable("forgot_password") {
            ForgotPasswordScreen(
                authViewModel = authViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        // ── Home ─────────────────────────────────────────────────────────────
        composable("home") {
            HomeScreen(
                authViewModel = authViewModel,
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}
