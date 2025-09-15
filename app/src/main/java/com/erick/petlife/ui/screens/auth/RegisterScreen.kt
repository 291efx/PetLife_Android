package com.erick.petlife.ui.screens.auth

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erick.petlife.R

@Composable
fun RegisterScreen(
    onBack: () -> Unit,
    vm: AuthViewModel
) {
    // Estado UI
    var name by remember { mutableStateOf("") } // opcional (lo guardaremos más adelante en Firestore)
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var showPass by remember { mutableStateOf(false) }
    var showConfirm by remember { mutableStateOf(false) }

    val loading by vm.loading.collectAsState()
    val error by vm.error.collectAsState()

    // Validaciones mínimas
    val emailOk = email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val passOk = pass.length >= 6
    val matches = confirm == pass && confirm.isNotBlank()
    val canSubmit = emailOk && passOk && matches && !loading

    // Colores
    val gradientTop = Color(0xFFFFA000)
    val gradientBottom = Color(0xFFF44336)
    val orange = Color(0xFFFF9500)
    val orangeDisabled = Color(0xFFFFD199)
    val labelGray = Color(0xFF8E8E93)
    val borderFocused = Color(0xFFB9B9BB)
    val borderUnfocused = Color(0xFFD9D9DD)

    // Fondo degradado
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(gradientTop, gradientBottom))),
        contentAlignment = Alignment.Center
    ) {
        // Tarjeta
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .wrapContentHeight(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo
                Box(
                    modifier = Modifier
                        .size(300.dp)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_petlife_logo),
                        contentDescription = "PetLife Logo",
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    "Crear cuenta",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFF1C1C1E),
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.height(16.dp))

                // Nombre (opcional)
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre (opcional)") },
                    leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = null) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = borderFocused,
                        unfocusedBorderColor = borderUnfocused,
                        focusedLabelColor = labelGray,
                        unfocusedLabelColor = labelGray
                    )
                )

                Spacer(Modifier.height(12.dp))

                // Correo
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Correo electrónico") },
                    leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = borderFocused,
                        unfocusedBorderColor = borderUnfocused,
                        focusedLabelColor = labelGray,
                        unfocusedLabelColor = labelGray
                    )
                )

                Spacer(Modifier.height(12.dp))

                // Contraseña
                OutlinedTextField(
                    value = pass,
                    onValueChange = { pass = it },
                    label = { Text("Contraseña (mín. 6)") },
                    leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = null) },
                    trailingIcon = {
                        IconButton(onClick = { showPass = !showPass }) {
                            Icon(
                                if (showPass) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                                contentDescription = if (showPass) "Ocultar" else "Mostrar"
                            )
                        }
                    },
                    singleLine = true,
                    visualTransformation = if (showPass) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = borderFocused,
                        unfocusedBorderColor = borderUnfocused,
                        focusedLabelColor = labelGray,
                        unfocusedLabelColor = labelGray
                    )
                )

                Spacer(Modifier.height(12.dp))

                // Confirmar contraseña
                OutlinedTextField(
                    value = confirm,
                    onValueChange = { confirm = it },
                    label = { Text("Confirmar contraseña") },
                    leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = null) },
                    trailingIcon = {
                        IconButton(onClick = { showConfirm = !showConfirm }) {
                            Icon(
                                if (showConfirm) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                                contentDescription = if (showConfirm) "Ocultar" else "Mostrar"
                            )
                        }
                    },
                    singleLine = true,
                    visualTransformation = if (showConfirm) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = borderFocused,
                        unfocusedBorderColor = borderUnfocused,
                        focusedLabelColor = labelGray,
                        unfocusedLabelColor = labelGray
                    )
                )

                Spacer(Modifier.height(16.dp))

                // Botón Registrar
                Button(
                    onClick = { vm.register(email.trim(), pass) /* luego guardamos name en Firestore */ },
                    enabled = canSubmit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = orange,
                        contentColor = Color.White,
                        disabledContainerColor = orangeDisabled,
                        disabledContentColor = Color.White.copy(alpha = 0.7f)
                    )
                ) {
                    if (loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("Registrarme")
                    }
                }

                if (error != null) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        error!!,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(Modifier.height(10.dp))

                // Volver
                TextButton(onClick = onBack) {
                    Text("Volver", color = Color(0xFF0A84FF), fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}
