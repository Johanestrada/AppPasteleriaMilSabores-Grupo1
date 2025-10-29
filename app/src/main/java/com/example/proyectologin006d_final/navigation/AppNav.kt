package com.example.proyectologin006d_final.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectologin006d_final.ui.login.LoginScreen
import com.example.proyectologin006d_final.ui.registro.RegistroScreen
import com.example.proyectologin006d_final.view.MainScreen
import com.example.proyectologin006d_final.view.ProductoFormScreen

@Composable
fun AppNav(startDestination: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = "login") {
            LoginScreen(navController = navController)
        }

        composable(
            route = "home/{username}",
            arguments = listOf(
                navArgument("username") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username").orEmpty()
            MainScreen(username = username, mainNavController = navController)
        }

        // --- Pantalla de Formulario de Producto ---
        composable(route = "ProductoFormScreen") {
            ProductoFormScreen()
        }

        // --- Pantalla de Registro ---
        composable(route = "registro") {
            RegistroScreen(navController = navController)
        }
    }
}
