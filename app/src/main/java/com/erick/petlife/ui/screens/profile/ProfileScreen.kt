package com.erick.petlife.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.erick.petlife.ui.screens.auth.AuthViewModel

@Composable
fun ProfileScreen(padding: PaddingValues, vm: AuthViewModel = hiltViewModel()) {
    Column(Modifier.padding(padding).padding(24.dp)) {
        Text("Perfil", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))
        Button(onClick = { vm.logout() }) { Text("Cerrar sesión") }
    }
}
