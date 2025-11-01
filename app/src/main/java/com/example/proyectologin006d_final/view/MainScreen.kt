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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectologin006d_final.R
import com.example.proyectologin006d_final.navigation.BottomNavigationBar
import com.example.proyectologin006d_final.navigation.BottomNavItem
import com.example.proyectologin006d_final.ui.perfil.PerfilScreen
import com.example.proyectologin006d_final.ui.producto.ProductsScreen
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
                    containerColor = Color(0xFFFFCCBC)
                )
            )
        },
        bottomBar = { BottomNavigationBar(navController = bottomNavController) }
    ) { innerPadding ->
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
                        text = "Bienvenidos a Pastelería Mil Sabores, un lugar donde cada postre cuenta una historia. Fundada en 2025, nuestra pasión es crear momentos dulces e inolvidables para ti y tu familia. Usamos solo los ingredientes más frescos y de la más alta calidad, combinando recetas tradicionales con un toque de innovación. ¡Gracias por ser parte de nuestro sueño!",
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
