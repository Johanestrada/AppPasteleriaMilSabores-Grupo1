package com.example.proyectologin006d_final.ui.perfil

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.proyectologin006d_final.R
import com.example.proyectologin006d_final.data.model.Usuario
import com.example.proyectologin006d_final.util.SessionManager

@Composable
fun PerfilScreen(mainNavController: NavController) {
    val viewModel: PerfilViewModel = viewModel()
    val usuario by viewModel.user.collectAsStateWithLifecycle()
    val photoUri by viewModel.photoUri.collectAsStateWithLifecycle()

    val navBackStackEntry = mainNavController.currentBackStackEntry
    LaunchedEffect(navBackStackEntry) {
        val uri = navBackStackEntry?.savedStateHandle?.get<Uri>("photo_uri")
        uri?.let {
            viewModel.updatePhotoUri(it)
            navBackStackEntry.savedStateHandle.remove<Uri>("photo_uri")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8F0)), // Fondo pastel
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileHeader(
            usuario = usuario,
            photoUri = photoUri,
            onImageClick = { mainNavController.navigate("tomar_foto") }
        )

        Spacer(modifier = Modifier.height(24.dp))

        ProfileMenu()

        Spacer(modifier = Modifier.weight(1f))

        LogoutButton(navController = mainNavController)
    }
}

@Composable
fun ProfileHeader(usuario: Usuario?, photoUri: String?, onImageClick: () -> Unit) {
    Spacer(modifier = Modifier.height(48.dp))

    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
            .clickable(onClick = onImageClick),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = photoUri,
            contentDescription = "Foto de perfil",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = painterResource(id = R.drawable.ic_launcher_foreground)
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = usuario?.nombreCompleto ?: "Cargando nombre...",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    Text(
        text = usuario?.correo ?: "Cargando correo...",
        fontSize = 16.sp,
        color = Color.Gray
    )
}

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
