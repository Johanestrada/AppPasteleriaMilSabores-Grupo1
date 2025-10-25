package com.example.proyectologin006d_final.view

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@Composable
fun DrawerMenu(username: String, navController: NavController) {
    val pastelBackground = Color(0xFFFFF8F0)
    val pastelCard = Color(0xFFFFE0E0)
    val pastelText = Color(0xFF5D4037)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(pastelBackground)
    ) {
        // 🔷 Encabezado visual
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(Color(0xFFFFCCBC)) // pastel naranja claro
        ) {
            Text(
                text = "Hola, $username 👋",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF4E342E),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
        }

        // 🔷 Lista de productos
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val productos = listOf(
                Triple("Pastel de Vainilla", "5000", Icons.Default.Cake),
                Triple("Pastel de Chocolate", "5500", Icons.Default.Star),
                Triple("Pastel de Frutilla", "5200", Icons.Default.Favorite),
                Triple("Pastel de Zanahoria", "5300", Icons.Default.BakeryDining),
                Triple("Pastel de Limón", "6000", Icons.Default.EmojiFoodBeverage)
            )

            productos.forEach { (nombre, precio, icono) ->
                item {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = pastelCard),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(horizontal = 4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp)
                                .clickable() {
                                    val encodedNombre = Uri.encode(nombre)
                                    navController.navigate("ProductoFormScreen/$encodedNombre/$precio")
                                }
                        ) {
                            Icon(
                                imageVector = icono,
                                contentDescription = nombre,
                                tint = Color(0xFF6D4C41),
                                modifier = Modifier
                                    .size(32.dp)
                                    .padding(end = 16.dp)
                            )
                            Column {
                                Text(
                                    text = nombre,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = pastelText
                                )
                                Text(
                                    text = "$precio CLP",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = pastelText
                                )
                            }
                        }
                    }
                }
            }
        }

        // 🔷 Pie de página
        Text(
            text = " @ 2025 Pasteleria 1000 Sabores",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}
