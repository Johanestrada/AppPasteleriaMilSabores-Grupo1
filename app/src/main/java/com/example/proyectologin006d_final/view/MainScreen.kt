package com.example.proyectologin006d_final.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectologin006d_final.R
import com.example.proyectologin006d_final.navigation.BottomNavigationBar
import com.example.proyectologin006d_final.navigation.BottomNavItem
import com.example.proyectologin006d_final.ui.producto.ProductsScreen
import com.example.proyectologin006d_final.ui.perfil.PerfilScreen
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(username: String, mainNavController: NavController) {
    val bottomNavController = rememberNavController()
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    OutlinedTextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        placeholder = { Text("Buscar pasteles...") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Icono de búsqueda") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true
                    )
                },
                actions = {
                    // Iconos para lista de deseos, carrito y notificaciones.
                    IconButton(onClick = { /* TODO: Navegar a lista de deseos */ }) {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = "Lista de Deseos")
                    }
                    IconButton(onClick = { /* TODO: Navegar al carrito */ }) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito de Compras")
                    }
                    IconButton(onClick = { /* TODO: Mostrar notificaciones */ }) {
                        Icon(Icons.Default.NotificationsNone, contentDescription = "Notificaciones")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFCCBC) // Color pastel para la barra
                )
            )
        },
        bottomBar = { BottomNavigationBar(navController = bottomNavController) }
    ) { innerPadding ->
        // El contenido de las pestañas se muestra aquí, aplicando el padding del Scaffold.
        Box(modifier = Modifier.padding(innerPadding)) {
            BottomNavGraph(
                navController = bottomNavController,
                mainNavController = mainNavController,
                username = username
            )
        }
    }
}

@Composable
fun BottomNavGraph(navController: NavHostController, mainNavController: NavController, username: String) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            // Pasamos el nombre de usuario a la pestaña de Home.
            HomeTabContent(navController = mainNavController, username = username)
        }
        composable(BottomNavItem.Productos.route) {
            ProductsScreen()
        }
        composable(BottomNavItem.Perfil.route) {
            PerfilScreen(mainNavController = mainNavController)
        }
    }
}

@Composable
fun HomeTabContent(navController: NavController, username: String) {
    val pastelBackground = Color(0xFFFFF8F0)
    val pastelCard = Color(0xFFFFE0E0)
    val pastelText = Color(0xFF5D4037)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(pastelBackground)
    ) {
        // Encabezado de bienvenida
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFCCBC)) // Fondo suave
                    .padding(start = 16.dp, top = 8.dp, bottom = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
            }
        }
        item {
            StoreInfoSection(modifier = Modifier.padding(vertical = 16.dp))
        }
        item {
            ProductCarousel(modifier = Modifier.padding(top = 24.dp, bottom = 12.dp))
        }
        item {
            Text(
                text = "Nuestros Pasteles",
                style = MaterialTheme.typography.titleLarge,
                color = pastelText,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        // Lista de productos
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
                    .padding(horizontal = 12.dp, vertical = 6.dp)
                    .clickable { navController.navigate("ProductoFormScreen") }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Icon(
                        imageVector = icono,
                        contentDescription = nombre,
                        tint = Color(0xFF6D4C41),
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = nombre, style = MaterialTheme.typography.titleMedium, color = pastelText)
                        Text(text = "$precio CLP", style = MaterialTheme.typography.bodySmall, color = pastelText)
                    }
                }
            }
        }

        // Pie de página
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

// --- 4. COMPONENTES REUTILIZABLES (SIN CAMBIOS) ---
@Composable
fun StoreInfoSection(modifier: Modifier = Modifier) {
    val pastelText = Color(0xFF5D4037)
    val storeOptions = listOf(
        Pair(Icons.Default.LocationOn, "Ubicación"),
        Pair(Icons.Default.Phone, "Llámanos"),
        Pair(Icons.Default.Share, "Redes")
    )
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        storeOptions.forEach { (icon, text) ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { /* Lógica futura */ }
            ) {
                Icon(imageVector = icon, contentDescription = text, tint = pastelText, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = text, style = MaterialTheme.typography.bodySmall, color = pastelText)
            }
        }
    }
}@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductCarousel(modifier: Modifier = Modifier) {
    // Lista de imágenes y textos correspondientes
    val imageList = listOf(R.drawable.tcuadrada, R.drawable.pastelblanco, R.drawable.tortatresleches)
    val textList = listOf("Nuestros Clásicos", "Especial de la Casa", "Postres que Enamoran")

    val pagerState = rememberPagerState(pageCount = { imageList.size })

    // Efecto para el auto-scroll (el "time" de cada imagen)
    LaunchedEffect(pagerState) {
        while (true) {
            delay(4000) // Espera 4 segundos
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            // Anima el scroll a la siguiente página
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Pager horizontal que contiene las imágenes
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentPadding = PaddingValues(horizontal = 32.dp), // Padding para ver un poco de las tarjetas de los lados
            pageSpacing = 16.dp // Espacio entre las páginas
        ) { page ->
            // Box para superponer la imagen, el gradiente y el texto
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(24.dp)) // Bordes redondeados para cada tarjeta del carrusel
            ) {
                // 1. Imagen de fondo
                Image(
                    painter = painterResource(id = imageList[page]),
                    contentDescription = textList[page],
                    contentScale = ContentScale.Crop, // Escala la imagen para que llene el contenedor
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                                startY = 300f // Inicia el gradiente más abajo para un efecto sutil
                            )
                        )
                )

                // 3. Texto superpuesto
                Text(
                    text = textList[page],
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.White, // Texto blanco
                        fontWeight = FontWeight.Bold // Texto en negrita
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomCenter) // Alineado abajo y al centro
                        .padding(16.dp) // Padding para que no toque los bordes
                )
            }
        }

        // Indicador de puntos (pager dots)
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
