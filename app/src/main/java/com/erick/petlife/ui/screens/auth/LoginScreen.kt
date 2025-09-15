package com.erick.petlife.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(onGoRegister: () -> Unit, onLoginSuccess: () -> Unit) {
    Column(Modifier.padding(24.dp)) {
        Text("Login (placeholder)")
        Spacer(Modifier.height(8.dp))
        Button(onClick = onLoginSuccess) { Text("Entrar (temporal)") }
        TextButton(onClick = onGoRegister) { Text("Crear cuenta") }
    }
}
