package com.example.proyectologin006d_final.ui.carrito

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CarritoScreen(navController: NavController) {
    val pastelBackground = Color(0xFFFFF8F0)
    val pastelCard = Color(0xFFFFE0E0)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(pastelBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = pastelCard),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Carrito vacío",
                        modifier = Modifier.size(64.dp),
                        tint = Color(0xFF5D4037)
                    )

                    Text(
                        text = "Tu carrito está vacío",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF5D4037)
                    )
                    
                    Text(
                        text = "Parece que aún no has agregado productos a tu carrito",
                        textAlign = TextAlign.Center,
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Button(
                        onClick = { 
                            navController.navigate("home/guest?fromCart=true") {
                                popUpTo("carrito") { inclusive = true }
                                launchSingleTop = true
                            }
                        },
                        modifier = Modifier.fillMaxWidth(0.7f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFCCBC),
                            contentColor = Color(0xFF5D4037)
                        )
                    ) {
                        Text("Ver catálogo")
                    }
                }
            }
        }
    }
}