package com.example.proyectologin006d_final.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectologin006d_final.viewmodel.CartViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectologin006d_final.ui.home.ProductosDestacados

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MuestraDatosScreen(
    username: String,
    navController: NavController
) {
    val pastelBackground = Color(0xFFFFF8F0)
    val pastelAccent = Color(0xFFFFCCBC)
    val pastelText = Color(0xFF5D4037)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfil de Usuario", color = pastelText) },
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
            val cartViewModel: CartViewModel = viewModel()
            ProductosDestacados(navController = navController, cartViewModel = cartViewModel)
            Text(
                text = "Bienvenido, $username 👋",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = pastelText
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    navController.navigate("login") {
                        popUpTo("login") { inclusive = true }
                        launchSingleTop = true
                    }
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = pastelAccent,
                    contentColor = pastelText
                ),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp)
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}
