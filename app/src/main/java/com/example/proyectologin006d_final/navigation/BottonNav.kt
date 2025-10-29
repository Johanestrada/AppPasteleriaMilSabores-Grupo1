package com.example.proyectologin006d_final.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,         // Ruta de navegación
    val title: String,         // Texto a mostrar
    val icon: ImageVector      // Ícono a mostrar
) {
    object Home : BottomNavItem(
        route = "home",
        title = "Inicio",
        icon = Icons.Default.Home
    )
    object Products : BottomNavItem(
        route = "products",
        title = "Productos",
        icon = Icons.Default.Storefront
    )
    object Camera : BottomNavItem(
        route = "camera",
        title = "Cámara",
        icon = Icons.Default.PhotoCamera
    )
    object Profile : BottomNavItem(
        route = "profile",
        title = "Perfil",
        icon = Icons.Default.Person
    )
}
