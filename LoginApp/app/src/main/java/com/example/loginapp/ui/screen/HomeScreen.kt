package com.example.loginapp.ui.screen

import androidx.biometric.BiometricManager
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginapp.viewmodel.AuthState
import com.example.loginapp.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(
    onLogout: () -> Unit = {},
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val user = FirebaseAuth.getInstance().currentUser
    val authState by authViewModel.authState.collectAsState()
    val biometricEnabled by authViewModel.biometricEnabled.collectAsState()

    val canUseBiometric = remember {
        BiometricManager.from(context)
            .canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK) ==
                BiometricManager.BIOMETRIC_SUCCESS
    }

    LaunchedEffect(authState) {
        if (authState is AuthState.LoggedOut) onLogout()
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier.size(96.dp),
                tint = Color(0xFF6650A4)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Welcome, ${user?.displayName ?: user?.email ?: "User"} 🎉",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6650A4)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = user?.email ?: "",
                fontSize = 13.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(40.dp))

            // Biometric toggle (only shown if hardware is available)
            if (canUseBiometric) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F0FF))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Fingerprint, null,
                                tint = Color(0xFF6650A4), modifier = Modifier.size(28.dp))
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text("Biometric Login", fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp)
                                Text("Use fingerprint / face to sign in",
                                    fontSize = 11.sp, color = Color.Gray)
                            }
                        }
                        Switch(
                            checked = biometricEnabled,
                            onCheckedChange = { authViewModel.setBiometricEnabled(it) },
                            colors = SwitchDefaults.colors(checkedThumbColor = Color(0xFF6650A4),
                                checkedTrackColor = Color(0xFFD0BCFF))
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            Button(
                onClick = { authViewModel.logout() },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6650A4))
            ) {
                Text("Logout", fontSize = 16.sp)
            }
        }
    }
}
