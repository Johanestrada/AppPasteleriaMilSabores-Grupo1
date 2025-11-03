// ruta: app/src/main/java/com/example/proyectologin006d_final/navigation/AppNav.kt

// ... (importaciones)
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectologin006d_final.ui.carrito.CarritoScreen
import com.example.proyectologin006d_final.viewmodel.CartViewModel

@Composable
fun AppNav(startDestination: String) {
    val navController = rememberNavController()

    // Se crea UNA SOLA instancia del ViewModel aquí.
    val cartViewModel: CartViewModel = viewModel()

    NavHost(navController = navController, startDestination = startDestination) {
        // ... (composable de login)

        composable(route = "home/{username}?fromCart={fromCart}" /*...*/) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username").orEmpty()

            // Se pasa la instancia compartida a MainScreen.
            MainScreen(
                username = username,
                mainNavController = navController,
                cartViewModel = cartViewModel // <-- CORRECTO
            )
        }

        // ... (otros composables)

        composable(route = "carrito") {
            // Se pasa LA MISMA instancia a CarritoScreen.
            CarritoScreen(
                navController = navController,
                cartViewModel = cartViewModel // <-- CORRECTO
            )
        }
    }
}
