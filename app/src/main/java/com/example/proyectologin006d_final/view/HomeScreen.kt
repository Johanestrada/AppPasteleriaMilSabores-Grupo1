package com.example.proyectologin006d_final.view

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // ✨ Importante: cambiamos a items para una mejor práctica
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectologin006d_final.R // ✨ ¡Asegúrate de que este import sea correcto para tu proyecto!
import com.example.proyectologin006d_final.util.SessionManager
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(username: String, navController: NavController) {
    val pastelBackground = Color(0xFFFFF8F0)
    val pastelCard = Color(0xFFFFE0E0)
    val pastelText = Color(0xFF5D4037)
    val context = LocalContext.current // Obtenemos el contexto actual

    // Usamos LazyColumn como el contenedor principal para que TODA la pantalla se desplace
    // si el contenido es más grande que la pantalla.
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(pastelBackground)
    ) {
        // --- 🔷 Encabezado visual (se mantiene igual) ---
        item {
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
        }

        // --- ✨ AQUÍ VA EL CARRUSEL DE PRODUCTOS ---
        item {
            ProductCarousel(modifier = Modifier.padding(top = 24.dp, bottom = 12.dp))
        }

        // --- 🔷 Título para la lista de productos ---
        item {
            Text(
                text = "Nuestros Pasteles",
                style = MaterialTheme.typography.titleLarge,
                color = pastelText,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        // --- 🔷 Lista de productos (se mantiene tu lógica) ---
        val productos = listOf(
            Triple("Pastel de Vainilla", "5000", Icons.Default.Cake),
            Triple("Pastel de Chocolate", "5500", Icons.Default.Star),
            Triple("Pastel de Frutilla", "5200", Icons.Default.Favorite),
            Triple("Pastel de Zanahoria", "5300", Icons.Default.BakeryDining),
            Triple("Pastel de Limón", "6000", Icons.Default.EmojiFoodBeverage)
        )

        items(productos) { (nombre, precio, icono) ->
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = pastelCard),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(horizontal = 12.dp, vertical = 6.dp) // Pequeño ajuste de padding
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .clickable {
                            navController.navigate("ProductoFormScreen")
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

        // --- 🔷 Botón de Cerrar Sesión ---
        item {
            Spacer(modifier = Modifier.height(16.dp)) // Un poco de espacio
            TextButton(
                onClick = {
                    // Limpiamos la sesión
                    SessionManager.clearSession(context)
                    // Navegamos al login y limpiamos el backstack
                    navController.navigate("login") {
                        popUpTo(0) // Limpia todas las pantallas anteriores
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                Text("Cerrar Sesión", color = Color.Red)
            }
        }

        // --- 🔷 Pie de página (se mantiene igual) ---
        item {
            Text(
                text = "@ 2025 Pasteleria 1000 Sabores",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp), // Más padding para separarlo
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ProductCarousel(modifier: Modifier = Modifier) {
    val imageList = listOf(
        R.drawable.tcuadrada,
        R.drawable.pastelblanco,
        R.drawable.tortatresleches
    )
    // --- 1. Creamos una lista con los textos para cada imagen ---
    val textList = listOf(
        "Nuestros Clásicos",
        "Especial de la Casa",
        "Postres que Enamoran"
    )

    val pagerState = rememberPagerState(pageCount = { imageList.size })

    // La lógica de auto-scroll debe estar dentro de un LaunchedEffect
    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000) // Espera 3 segundos
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            pageSpacing = 16.dp
        ) { page ->
            // --- 2. Cada página ahora es un Box para poder apilar elementos ---
            Box(modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp)) // Añadimos bordes redondeados al carrusel
            ) {
                // La imagen va primero, para que quede de fondo
                Image(
                    painter = painterResource(id = imageList[page]),
                    contentDescription = textList[page],
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // --- 3. Añadimos un gradiente oscuro para asegurar legibilidad ---
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                                startY = 300f // El gradiente empieza más abajo
                            )
                        )
                )

                // --- 4. El texto va al final, para que quede encima de todo ---
                Text(
                    text = textList[page], // Usamos el texto de nuestra nueva lista
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomCenter) // Lo alineamos abajo y al centro
                        .padding(16.dp) // Con un poco de espacio
                )
            }
        }

    }
}
