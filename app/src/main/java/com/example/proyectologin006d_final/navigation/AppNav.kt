package com.example.proyectologin006d_final.navigation

import RegistroScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectologin006d_final.ui.camera.TomarFotoScreen
import com.example.proyectologin006d_final.ui.login.LoginScreen
import com.example.proyectologin006d_final.view.MainScreen
import com.example.proyectologin006d_final.view.ProductoFormScreen
import com.example.proyectologin006d_final.ui.carrito.CarritoScreen

@Composable
fun AppNav(startDestination: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = "login") {
            LoginScreen(navController = navController)
        }

        composable(
            route = "home/{username}?fromCart={fromCart}",
            arguments = listOf(
                navArgument("username") {
                    type = NavType.StringType
                },
                navArgument("fromCart") {
                    type = NavType.StringType
                    defaultValue = "false"
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username").orEmpty()
            MainScreen(username = username, mainNavController = navController)
        }

        composable("tomar_foto") {
            TomarFotoScreen { uri ->
                navController.previousBackStackEntry?.savedStateHandle?.set("photo_uri", uri)
                navController.popBackStack()
            }
        }

        // --- Pantalla de Formulario de Producto ---
        composable(route = "ProductoFormScreen") {
            ProductoFormScreen()
        }

        // --- Pantalla de Registro ---
        composable(route = "registro") {
            RegistroScreen(navController = navController)
        }

        // --- Pantalla del Carrito ---
        composable(route = "carrito") {
            CarritoScreen(navController = navController)
        }
    }
}
