package com.example.proyectologin006d_final.ui.perfil

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.proyectologin006d_final.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectologin006d_final.data.model.Usuario
import com.example.proyectologin006d_final.util.SessionManager

@Composable
fun PerfilScreen(mainNavController: NavController, perfilViewModel: PerfilViewModel = viewModel()) {
    val context = LocalContext.current
    val usuario by perfilViewModel.user.collectAsStateWithLifecycle()

    var photoUri by rememberSaveable { mutableStateOf<Uri?>(null) }

    LaunchedEffect(Unit) {
        SessionManager.getPhotoUri(context)?.let {
            photoUri = Uri.parse(it)
        }
    }

    mainNavController.currentBackStackEntry
        ?.savedStateHandle
        ?.get<Uri>("photo_uri")?.let { uri ->
            photoUri = uri
            SessionManager.savePhotoUri(context, uri.toString())
            mainNavController.currentBackStackEntry
                ?.savedStateHandle
                ?.remove<Uri>("photo_uri")
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            if (photoUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(photoUri),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Text(text = "No hay foto de perfil", modifier = Modifier.align(Alignment.Center))
            }
            var showMenu by remember { mutableStateOf(false) }

            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar foto",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(4.dp)
                    .clickable { showMenu = true }
            )

            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Quitar foto") },
                    onClick = {
                        photoUri = null
                        SessionManager.clearPhotoUri(context)
                        showMenu = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Tomar foto") },
                    onClick = {
                        mainNavController.navigate("tomar_foto")
                        showMenu = false
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // No hay botón de "Tomar foto" aquí, la funcionalidad se moverá al icono de lápiz.
        usuario?.let {
            Text(text = "Nombre: ${it.nombreCompleto}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Correo: ${it.correo}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Fecha de nacimiento: ${it.fechaNacimiento}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Región: ${it.region}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Comuna: ${it.comuna}")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                SessionManager.clearSession(context)
                mainNavController.navigate("login") {
                    popUpTo(0)
                }
            }
        ) {
            Text("Cerrar Sesión", color = Color.White)
        }
    }
}
