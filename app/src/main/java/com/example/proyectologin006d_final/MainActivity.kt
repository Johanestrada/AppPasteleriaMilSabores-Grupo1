// ruta: app/src/main/java/com/example/proyectologin006d_final/navigation/AppNav.kt

package com.example.proyectologin006d_final.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel // <- ¡IMPORTACIÓN CRÍTICA!
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectologin006d_final.ui.camera.TomarFotoScreen
import com.example.proyectologin006d_final.ui.carrito.CarritoScreen
import com.example.proyectologin006d_final.ui.login.LoginScreen
import com.example.proyectologin006d_final.ui.registro.RegistroScreen
import com.example.proyectologin006d_final.view.MainScreen
import com.example.proyectologin006d_final.view.ProductoFormScreen
import com.example.proyectologin006d_final.viewmodel.CartViewModel

@Composable
fun AppNav(startDestination: String) {
    val navController = rememberNavController()

    // 1. CREAMOS UNA SOLA INSTANCIA DEL VIEWMODEL AQUÍ
    val cartViewModel: CartViewModel = viewModel()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = "login") {
            LoginScreen(navController = navController)
        }

        composable(
            route = "home/{username}?fromCart={fromCart}",
            arguments = listOf(
                navArgument("username") { type = NavType.StringType },
                navArgument("fromCart") {
                    type = NavType.StringType
                    defaultValue = "false"
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username").orEmpty()

            // 2. CORRECCIÓN: PASAMOS LA INSTANCIA DEL VIEWMODEL A MAINSCREEN
            MainScreen(
                username = username,
                mainNavController = navController,
                cartViewModel = cartViewModel // <-- Parámetro añadido
            )
        }

        composable("tomar_foto") {
            TomarFotoScreen { uri ->
                navController.previousBackStackEntry?.savedStateHandle?.set("photo_uri", uri)
                navController.popBackStack()
            }
        }

        composable(route = "ProductoFormScreen") {
            ProductoFormScreen()
        }

        composable(route = "registro") {
            RegistroScreen(navController = navController)
        }

        composable(route = "carrito") {
            // 3. PASAMOS LA MISMA INSTANCIA A CARRITOSCREEN
            CarritoScreen(
                navController = navController,
                cartViewModel = cartViewModel
            )
        }
    }
}
