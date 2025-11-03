// app/src/main/java/com/example/proyectologin006d_final/view/MainScreen.kt

package com.example.proyectologin006d_final.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectologin006d_final.R
import com.example.proyectologin006d_final.navigation.BottomNavigationBar
import com.example.proyectologin006d_final.navigation.BottomNavItem
import com.example.proyectologin006d_final.ui.perfil.PerfilScreen
import com.example.proyectologin006d_final.ui.producto.ProductsScreen
import com.example.proyectologin006d_final.viewmodel.CartViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(username: String, mainNavController: NavController, cartViewModel: CartViewModel) {
    // 1. Instanciamos el CartViewModel. `viewModel()` asegura una única instancia compartida.
    val cartViewModel: CartViewModel = viewModel()

    // 2. Creamos un NavController solo para la barra de navegación inferior.
    val bottomNavController = rememberNavController()

    // 3. Utilizamos un único Scaffold para toda la pantalla principal.
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mil Sabores") },
                actions = {
                    IconButton(onClick = { /* TODO: Lista de deseos */ }) {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = "Lista de Deseos")
                    }
                    // Usamos el 'mainNavController' para ir a la ruta del carrito en AppNav.kt
                    IconButton(onClick = { mainNavController.navigate("carrito") }) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito de Compras")
                    }
                    IconButton(onClick = { /* TODO: Notificaciones */ }) {
                        Icon(Icons.Default.NotificationsNone, contentDescription = "Notificaciones")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFCCBC))
            )
        },
        bottomBar = {
            // La barra de navegación inferior controla las pestañas principales.
            BottomNavigationBar(navController = bottomNavController)
        }
    ) { innerPadding ->
        // NavHost para las pestañas inferiores (Home, Productos, Perfil).
        // Este NavHost vive dentro del Scaffold principal.
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding) // Aplica el padding del Scaffold
        ) {
            composable(BottomNavItem.Home.route) {
                // El contenido de la pantalla de inicio.
                HomeScreenContent(username = username)
            }
            composable(BottomNavItem.Productos.route) {
                // La pantalla de productos recibe el ViewModel para añadir al carrito.
                ProductsScreen(cartViewModel = cartViewModel)
            }
            composable(BottomNavItem.Perfil.route) {
                // La pantalla de perfil usa el 'mainNavController' para acciones como cerrar sesión.
                PerfilScreen(mainNavController = mainNavController)
            }
        }
    }
}

// Contenido exclusivo de la pantalla de Inicio
@Composable
fun HomeScreenContent(username: String) {
    val pastelBackground = Color(0xFFFFF8F0)
    val pastelCard = Color(0xFFFFE0E0)
    val pastelText = Color(0xFF5D4037)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(pastelBackground)
    ) {
        item {
            StoreInfoSection(modifier = Modifier.padding(vertical = 16.dp))
        }
        item {
            ProductCarousel(modifier = Modifier.padding(top = 8.dp, bottom = 12.dp))
        }
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = pastelCard),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Sobre Nosotros",
                        style = MaterialTheme.typography.titleLarge,
                        color = pastelText,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Bienvenidos a Pastelería Mil Sabores. Desde 2025, endulzamos tus momentos con recetas tradicionales y creaciones únicas, usando siempre los ingredientes más frescos.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = pastelText
                    )
                }
            }
        }
        item {
            Text(
                text = "@ 2025 Pasteleria Mil Sabores",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

// --- Componentes reusables de la pantalla de inicio ---

@Composable
fun StoreInfoSection(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val pastelText = Color(0xFF5D4037)
    val storeOptions = listOf(
        Triple(Icons.Default.LocationOn, "Ubicación", "geo:0,0?q=Duoc UC: Sede Puente Alto"),
        Triple(Icons.Default.Phone, "Llámanos", "tel:+56912345678"),
        Triple(Icons.Default.Share, "Redes", "https://www.instagram.com") // URL de ejemplo
    )

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        storeOptions.forEach { (icon, text, actionUri) ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(actionUri))
                    context.startActivity(intent)
                }
            ) {
                Icon(imageVector = icon, contentDescription = text, tint = pastelText, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = text, style = MaterialTheme.typography.bodySmall, color = pastelText)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductCarousel(modifier: Modifier = Modifier) {
    val imageList = listOf(R.drawable.tcuadrada, R.drawable.pastelblanco, R.drawable.tortatresleches)
    val textList = listOf("Nuestros Clásicos", "Especial de la Casa", "Postres que Enamoran")
    val pagerState = rememberPagerState(pageCount = { imageList.size })

    LaunchedEffect(pagerState) {
        while (true) {
            delay(4000)
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
            contentPadding = PaddingValues(horizontal = 32.dp),
            pageSpacing = 16.dp
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(24.dp))
            ) {
                Image(
                    painter = painterResource(id = imageList[page]),
                    contentDescription = textList[page],
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                                startY = 300f
                            )
                        )
                )
                Text(
                    text = textList[page],
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                )
            }
        }

        Row(
            Modifier
                .height(20.dp)
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color(0xFF6D4C41) else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color)
                        .size(10.dp)
                )
            }
        }
    }
}

