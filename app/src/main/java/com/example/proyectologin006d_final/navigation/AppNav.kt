package com.example.proyectologin006d_final.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
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
fun AppNav() {
    val navController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
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
            MainScreen(
                username = username,
                mainNavController = navController,
                cartViewModel = cartViewModel
            )
        }

        composable(route = "tomar_foto") {
            TomarFotoScreen(navController = navController)
        }

        composable(route = "ProductoFormScreen") {
            ProductoFormScreen()
        }

        composable(route = "registro") {
            RegistroScreen(navController = navController)
        }

        composable(route = "carrito") {
            CarritoScreen(
                navController = navController,
                cartViewModel = cartViewModel
            )
        }
    }
}
