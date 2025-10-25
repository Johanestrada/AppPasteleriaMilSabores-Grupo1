package com.example.proyectologin006d_final.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectologin006d_final.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    vm: LoginViewModel = viewModel()
) {
    val state = vm.uiState
    var showPass by remember { mutableStateOf(false) }

    // 🎨 Paleta de colores pastel
    val pastelBackground = Color(0xFFFFF8F0)
    val pastelAccent = Color(0xFFFFCCBC)
    val pastelText = Color(0xFF5D4037)

    // 🌈 Aplicar tema personalizado
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = pastelAccent,
            onPrimary = Color.White,
            surface = pastelBackground,
            onSurface = pastelText
        )
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Pastelería App",
                            color = MaterialTheme.colorScheme.onPrimary

                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = pastelAccent)
                )
            },
            containerColor = pastelBackground
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //  Título de bienvenida
                Text(
                    text = "Bienvenido ",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = pastelText
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                //  Logo
                Image(
                    painter = painterResource(id = R.drawable.logoduoc),
                    contentDescription = "Logo App",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(32.dp))

                //  Campo de correo
                OutlinedTextField(
                    value = state.correo,
                    onValueChange = vm::onCorreoChange,
                    label = { Text("Correo") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.95f)
                )

                //  Botton el cual deja mostrar si acaso el texto de la contraseña sea visible
                OutlinedTextField(
                    value = state.clave,
                    onValueChange = vm::onClaveChange,
                    label = { Text("Contraseña") },
                    singleLine = true,
                    visualTransformation = if (showPass) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        TextButton(onClick = { showPass = !showPass }) {
                            Text(if (showPass) "Ocultar" else "Ver")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.95f)
                )

                // Si llega a existir un error se deberia de enviar un mensaje
                if (state.mensaje.isNotEmpty()) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = state.mensaje,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        vm.submit { rol, nombre ->
                            if (rol == "admin") {
                                navController.navigate("admin")
                            } else {
                                navController.navigate("usuario/$nombre")
                            }
                        }
                    },
                    enabled = !state.isLoading,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = pastelAccent,
                        contentColor = pastelText
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(50.dp)
                ) {
                    Text(if (state.isLoading) "Validando..." else "Iniciar sesión")
                }

                Spacer(modifier = Modifier.height(12.dp))

                //  Enlace a registro
                TextButton(onClick = {
                    navController.navigate("register")
                }) {
                    Text("¿No tienes cuenta? Regístrate", color = pastelText)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    val vm = LoginViewModel()
    LoginScreen(navController = navController, vm = vm)
}
