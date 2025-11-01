package com.example.proyectologin006d_final.ui.perfil

// --- IMPORTACIONES (SIMPLIFICADAS) ---
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectologin006d_final.data.database.AppDataBase
import com.example.proyectologin006d_final.data.model.Usuario
import com.example.proyectologin006d_final.data.repository.UsuarioRepository
import com.example.proyectologin006d_final.util.SessionManager

// --- PANTALLA PRINCIPAL (SIMPLIFICADA) ---
@Composable
fun PerfilScreen(mainNavController: NavController) {
    val context = LocalContext.current

    // ✅ La creación del ViewModel con la Factory se mantiene, ¡es la parte clave!
    val viewModel: PerfilViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val db = AppDataBase.getDatabase(context)
                val repository = UsuarioRepository(db.usuarioDao())
                return PerfilViewModel(repository) as T
            }
        }
    )

    // Observamos el estado del usuario. Esto no cambia.
    val usuario by viewModel.user.collectAsStateWithLifecycle()

    // --- INTERFAZ DE USUARIO (SIN LÓGICA DE FOTOS) ---
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8F0)), // Fondo pastel
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Cabecera con foto (placeholder) y nombre del usuario.
        ProfileHeader(usuario = usuario)

        Spacer(modifier = Modifier.height(24.dp))

        // Menú de opciones.
        ProfileMenu()

        // Spacer que empuja el botón de cerrar sesión hacia abajo.
        Spacer(modifier = Modifier.weight(1f))

        // Botón de cerrar sesión.
        LogoutButton(navController = mainNavController)
    }
}


// --- COMPONENTES REUTILIZABLES (SIMPLIFICADOS) ---

@Composable
fun ProfileHeader(usuario: Usuario?) {
    // Espacio superior.
    Spacer(modifier = Modifier.height(48.dp))

    // ❌ Se eliminó el Box que contenía la lógica de la foto y el menú de edición.
    // Ahora solo mostramos una imagen estática de placeholder.


    Spacer(modifier = Modifier.height(16.dp))

    // Mostramos el nombre del usuario desde el ViewModel.
    Text(
        text = usuario?.nombreCompleto ?: "Cargando nombre...",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    // Mostramos el correo del usuario desde el ViewModel.
    Text(
        text = usuario?.correo ?: "Cargando correo...",
        fontSize = 16.sp,
        color = Color.Gray
    )
}

// Las funciones ProfileMenu, ProfileMenuItem y LogoutButton no necesitan cambios.
// Las mantengo aquí para que el archivo esté completo.

@Composable
fun ProfileMenu() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        ProfileMenuItem(text = "Editar Perfil") { /* TODO */ }
        ProfileMenuItem(text = "Mis Pedidos") { /* TODO */ }
        ProfileMenuItem(text = "Mis Direcciones") { /* TODO */ }
        ProfileMenuItem(text = "Métodos de Pago") { /* TODO */ }
    }
}

@Composable
fun ProfileMenuItem(text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, fontSize = 18.sp, modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = Color.Gray)
    }
    Divider()
}

@Composable
fun LogoutButton(navController: NavController) {
    val context = LocalContext.current
    Button(
        onClick = {
            SessionManager.clearSession(context)
            navController.navigate("login") { popUpTo(0) }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFFF0F0),
            contentColor = Color.Red
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Cerrar Sesión")
        Spacer(modifier = Modifier.width(8.dp))
        Text("Cerrar Sesión")
    }
}
