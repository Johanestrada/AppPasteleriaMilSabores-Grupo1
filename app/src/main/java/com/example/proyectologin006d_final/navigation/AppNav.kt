package com.example.proyectologin006d_final.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectologin006d_final.ui.login.LoginScreen
import com.example.proyectologin006d_final.view.DrawerMenu
import com.example.proyectologin006d_final.view.ProductoFormScreen

@Composable
fun AppNav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController = navController)
        }

        composable(
            route = "DrawerMenu/{username}",
            arguments = listOf(
                navArgument("username") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username").orEmpty()
            DrawerMenu(username = username, navController = navController)
        }

        // --- RUTA CORREGIDA PARA ProductoFormScreen ---
        // Ya no necesita recibir parámetros.
        composable(route = "ProductoFormScreen") {
            // Se llama a la pantalla sin parámetros, ya que ahora es un
            // formulario para crear productos nuevos.
            ProductoFormScreen()
        }
    }
}