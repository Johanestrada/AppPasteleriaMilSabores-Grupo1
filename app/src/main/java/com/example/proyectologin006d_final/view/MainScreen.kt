package com.example.proyectologin006d_final.view

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun MainScreen(username: String, mainNavController: NavController) {
    val bottomNavController = rememberNavController()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Hola, $username 👋") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFFFCCBC)
                )
            )
        },
        bottomBar = { BottomNavigationBar(navController = bottomNavController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            BottomNavGraph(navController = bottomNavController, mainNavController = mainNavController)
        }
    }
}

@Composable
fun BottomNavGraph(navController: NavHostController, mainNavController: NavController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeTabContent(navController = mainNavController)
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
fun StoreInfoSection(modifier: Modifier = Modifier) {
    val pastelText = Color(0xFF5D4037)
    val storeOptions = listOf(
        Pair(Icons.Default.LocationOn, "Ubicación"),
        Pair(Icons.Default.Phone, "Llámanos"),
        Pair(Icons.Default.Share, "Redes")
    )

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .height(55.dp) // Mantenemos la altura
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center, // Usamos Center para que los elementos estén juntos
            verticalAlignment = Alignment.CenterVertically
        ) {
            storeOptions.forEachIndexed { index, (icon, text) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .clickable { /* Lógica futura */ }
                        .padding(horizontal = 20.dp) // Aumentamos el padding para dar espacio
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = text,
                        tint = pastelText,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyMedium,
                        color = pastelText
                    )
                }

                if (index < storeOptions.size - 1) {
                    Divider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp),
                        color = Color.LightGray.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTabContent(navController: NavController) {
    val pastelBackground = Color(0xFFFFF8F0)
    val pastelCard = Color(0xFFFFE0E0)
    val pastelText = Color(0xFF5D4037)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(pastelBackground)
    ) {
        item {
            StoreInfoSection(modifier = Modifier.padding(horizontal = 16.dp))
        }
        item {
            ProductCarousel(modifier = Modifier.padding(bottom = 12.dp)) // Se elimina el padding superior
        }

        item {
            Text(
                text = "Nuestros Pasteles",
                style = MaterialTheme.typography.titleLarge,
                color = pastelText,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

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
                    .padding(horizontal = 12.dp, vertical = 6.dp)
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
                        modifier = Modifier.size(32.dp).padding(end = 16.dp)
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

        item {
            Text(
                text = "@ 2025 Pasteleria 1000 Sabores",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth().padding(32.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ProductCarousel(modifier: Modifier) {
    val imageList = listOf(
        R.drawable.tcuadrada,
        R.drawable.pastelblanco,
        R.drawable.tortatresleches
    )

    val pagerState = rememberPagerState(pageCount = { imageList.size })

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = " Novedades del Mes ",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF5D4037),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentPadding = PaddingValues(horizontal = 40.dp),
            pageSpacing = 16.dp
        ) { page ->
            Image(
                painter = painterResource(id = imageList[page]),
                contentDescription = "Producto destacado ${page + 1}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(24.dp))
            )
        }

        Row(
            Modifier
                .height(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(imageList.size) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color(0xFF6D4C41) else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color)
                        .size(10.dp)
                )
            }
        }
    }
}
