package com.erick.petlife.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen(onBack: () -> Unit, onRegistered: () -> Unit) {
    Column(Modifier.padding(24.dp)) {
        Text("Register (placeholder)")
        Spacer(Modifier.height(8.dp))
        Button(onClick = onRegistered) { Text("Registrarme (temporal)") }
        TextButton(onClick = onBack) { Text("Volver") }
    }
}
