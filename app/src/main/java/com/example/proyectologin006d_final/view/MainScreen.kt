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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectologin006d_final.R
import com.example.proyectologin006d_final.navigation.BottomNavigationBar
import com.example.proyectologin006d_final.navigation.BottomNavItem
import com.example.proyectologin006d_final.ui.carrito.CarritoScreen
import com.example.proyectologin006d_final.ui.perfil.PerfilScreen
import com.example.proyectologin006d_final.ui.producto.ProductsScreen
import com.example.proyectologin006d_final.viewmodel.CartViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(username: String, mainNavController: NavController) {
    // ---- INICIO CAMBIOS ----
    // 1. Instanciamos el CartViewModel. `viewModel()` se asegura que haya una única instancia.
    val cartViewModel: CartViewModel = viewModel()

    // 2. Creamos un nuevo NavController para la navegación principal (incluyendo el carrito)
    val appNavController = rememberNavController()
    // ---- FIN CAMBIOS ----

    Scaffold(
        topBar = {
            // El TopAppBar ahora usa el nuevo appNavController para ir al carrito
            TopAppBar(
                title = { Text("Mil Sabores") },
                actions = {
                    IconButton(onClick = { /* TODO: Lista de deseos */ }) {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = "Lista de Deseos")
                    }
                    // Ahora navega usando el controlador principal
                    IconButton(onClick = { appNavController.navigate("carrito") }) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito de Compras")
                    }
                    IconButton(onClick = { /* TODO: Notificaciones */ }) {
                        Icon(Icons.Default.NotificationsNone, contentDescription = "Notificaciones")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFCCBC))
            )
        },
        // El BottomBar sigue usando su propio NavController para las pestañas inferiores
        bottomBar = { BottomNavigationBar(navController = rememberNavController()) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            // ---- INICIO CAMBIOS ----
            // 3. Configuramos el NavHost principal que gestionará todas las pantallas
            NavHost(navController = appNavController, startDestination = "home_tab") {
                // Ruta para el contenido principal (con las pestañas inferiores)
                composable("home_tab") {
                    HomeTabContent(navController = appNavController, username = username, cartViewModel = cartViewModel)
                }
                // Nueva ruta para la pantalla del carrito
                composable("carrito") {
                    CarritoScreen(navController = appNavController, cartViewModel = cartViewModel)
                }
                composable("productos_tab") {
                    ProductsScreen(cartViewModel = cartViewModel)
                }
                composable("perfil_tab") {
                    PerfilScreen(mainNavController = appNavController)
                }
            }
            // ---- FIN CAMBIOS ----
        }
    }
}

// ---- INICIO CAMBIOS ----
// Se ha refactorizado para incluir el BottomNavGraph dentro de un NavHost más grande
@Composable
fun HomeTabContent(navController: NavController, username: String, cartViewModel: CartViewModel) {
    val bottomNavController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = bottomNavController) }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) {
                // Home principal (el que tenías antes)
                HomeScreenContent(navController = navController, username = username)
            }
            composable(BottomNavItem.Productos.route) {
                // La pantalla de productos ahora recibe el ViewModel
                ProductsScreen(cartViewModel = cartViewModel)
            }
            composable(BottomNavItem.Perfil.route) {
                PerfilScreen(mainNavController = navController)
            }
        }
    }
}

// El contenido original de HomeTabContent ahora está aquí
@Composable
fun HomeScreenContent(navController: NavController, username: String) {
// ---- FIN CAMBIOS ----
    val pastelBackground = Color(0xFFFFF8F0)
    val pastelCard = Color(0xFFFFE0E0)
    val pastelText = Color(0xFF5D4037)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(pastelBackground)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFCCBC))
                    .padding(start = 16.dp, top = 8.dp, bottom = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {}
        }
        item {
            StoreInfoSection(modifier = Modifier.padding(vertical = 16.dp))
        }
        item {
            ProductCarousel(modifier = Modifier.padding(top = 24.dp, bottom = 12.dp))
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
                        text = "Bienvenidos a Pastelería Mil Sabores...", // Texto completo
                        style = MaterialTheme.typography.bodyMedium,
                        color = pastelText
                    )
                }
            }
        }

        item {
            Text(
                text = "@ 2025 Pasteleria 1000 Sabores",
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


@Composable
fun StoreInfoSection(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val pastelText = Color(0xFF5D4037)
    val storeOptions = listOf(
        Triple(Icons.Default.LocationOn, "Ubicación", "geo:0,0?q=Duoc UC: Sede Puente Alto"),
        Triple(Icons.Default.Phone, "Llámanos", "tel:+56912345678"),
        Triple(Icons.Default.Share, "Redes", null)
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
                    actionUri?.let {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                        context.startActivity(intent)
                    }
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
