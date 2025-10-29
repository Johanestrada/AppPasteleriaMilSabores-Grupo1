package com.example.proyectologin006d_final.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectologin006d_final.util.SessionManager

@Composable
fun ProfileScreen(mainNavController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pantalla de Perfil")
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                // Limpiamos la sesión
                SessionManager.clearSession(context)
                // Navegamos al login y limpiamos el backstack
                mainNavController.navigate("login") {
                    popUpTo(0) // Limpia todas las pantallas anteriores
                }
            }
        ) {
            Text("Cerrar Sesión", color = Color.White)
        }
    }
}
