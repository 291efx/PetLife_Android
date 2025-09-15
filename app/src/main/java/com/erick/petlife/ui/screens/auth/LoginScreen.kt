package com.erick.petlife.ui.screens.auth

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
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
fun LoginScreen(
    onGoRegister: () -> Unit,
    vm: AuthViewModel
) {
    // Estado UI
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var showPass by remember { mutableStateOf(false) }

    val loading by vm.loading.collectAsState()
    val error by vm.error.collectAsState()

    // Validaciones mínimas
    val emailOk = email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val passOk = pass.length >= 6
    val canSubmit = emailOk && passOk && !loading

    // Colores UI (puedes moverlos a tu Theme si quieres)
    val gradientTop = Color(0xFFFFA000)   // naranja
    val gradientBottom = Color(0xFFF44336) // rojo
    val linkBlue = Color(0xFF0A84FF)       // link estilo iOS
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
        // Tarjeta blanca
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
                // Logo circular
                Box(
                    modifier = Modifier
                        .size(300.dp)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    // Si no tienes este recurso aún, comenta esta Image temporalmente
                    Image(
                        painter = painterResource(id = R.drawable.ic_petlife_logo),
                        contentDescription = "PetLife Logo",
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(Modifier.height(12.dp))

                // Subtítulo de bienvenida
                Text(
                    "Bienvenido 👋",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF3A3A3C)
                )
                Text(
                    "Inicia sesión para continuar",
                    style = MaterialTheme.typography.bodyMedium,
                    color = labelGray,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(20.dp))

                // Campo Email
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

                // Campo Contraseña
                OutlinedTextField(
                    value = pass,
                    onValueChange = { pass = it },
                    label = { Text("Contraseña") },
                    leadingIcon = { Icon(Icons.Outlined.Key, contentDescription = null) },
                    trailingIcon = {
                        IconButton(onClick = { showPass = !showPass }) {
                            Icon(
                                imageVector = if (showPass) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
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

                Spacer(Modifier.height(10.dp))

                // ¿Olvidaste tu contraseña?
                TextButton(onClick = { /* TODO: reset password con FirebaseAuth.sendPasswordResetEmail(email) */ }) {
                    Text(
                        "¿Olvidaste tu contraseña?",
                        color = linkBlue,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(Modifier.height(4.dp))

                // Botón Ingresar
                Button(
                    onClick = { vm.login(email, pass) },
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
                        Text("Ingresar")
                    }
                }

                // Error
                if (error != null) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        error!!,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(Modifier.height(10.dp))

                // Crear cuenta
                TextButton(onClick = onGoRegister) {
                    Text("Crear cuenta", color = Color(0xFFFF9500), fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}
