package com.example.proyectologin006d_final.view

import android.net.Uri
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
import com.example.proyectologin006d_final.ui.camera.QRScannerScreen
import com.example.proyectologin006d_final.ui.products.ProductsScreen
import com.example.proyectologin006d_final.ui.profile.ProfileScreen

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
        composable(BottomNavItem.Products.route) {
            ProductsScreen()
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen(mainNavController = mainNavController)
        }
        composable(BottomNavItem.Camera.route) {
            QRScannerScreen()
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
