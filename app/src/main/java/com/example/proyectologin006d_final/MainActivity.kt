package com.example.proyectologin006d_final

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.proyectologin006d_final.navigation.AppNav
import com.example.proyectologin006d_final.util.SessionManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Comprueba si hay una sesión activa
        val savedUsername = SessionManager.getSession(this)

        // Determina la ruta de inicio
        val startDestination = if (savedUsername != null) {
            // Si hay sesión, ve directo al menú principal
            "home/$savedUsername"
        } else {
            // Si no, ve a la pantalla de login
            "login"
        }

        setContent { AppNav(startDestination = startDestination) }
    }
}
