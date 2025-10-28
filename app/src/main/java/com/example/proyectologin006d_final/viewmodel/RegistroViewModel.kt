package com.example.proyectologin006d_final.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectologin006d_final.data.database.AppDataBase
import com.example.proyectologin006d_final.data.model.Usuario
import com.example.proyectologin006d_final.data.repository.UsuarioRepository
import kotlinx.coroutines.launch
import com.example.proyectologin006d_final.data.obtenerComunas // Asegúrate que este import esté
import com.example.proyectologin006d_final.data.regionesDeChile  // y este también
import kotlinx.coroutines.Dispatchers

class RegistroViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UsuarioRepository
    val regiones: List<String> = regionesDeChile
    var comunas by mutableStateOf<List<String>>(emptyList())
        private set

    init {
        val usuarioDao = AppDataBase.getDatabase(application).usuarioDao()
        repository = UsuarioRepository(usuarioDao)
    }

    // --- Estado del Formulario ---
    var nombreCompleto by mutableStateOf("")
        private set
    var fechaNacimiento by mutableStateOf("")
        private set
    var correo by mutableStateOf("")
        private set
    var contrasena by mutableStateOf("")
        private set
    var confirmarContrasena by mutableStateOf("")
        private set
    var telefono by mutableStateOf("")
        private set
    var region by mutableStateOf("")
        private set
    var comuna by mutableStateOf("")
        private set
    var codigoDescuento by mutableStateOf("")
        private set

    // --- Eventos de la UI ---
    fun onNombreChange(nuevoNombre: String) { nombreCompleto = nuevoNombre }
    fun onFechaNacimientoChange(nuevaFecha: String) { fechaNacimiento = nuevaFecha }
    fun onCorreoChange(nuevoCorreo: String) { correo = nuevoCorreo }
    fun onContrasenaChange(nuevaContrasena: String) { contrasena = nuevaContrasena }
    fun onConfirmarContrasenaChange(nuevaConfirmacion: String) { confirmarContrasena = nuevaConfirmacion }
    fun onTelefonoChange(nuevoTelefono: String) { telefono = nuevoTelefono }

    fun onRegionSelected(nuevaRegion: String) {
        region = nuevaRegion
        // ¡Esta es la línea que faltaba! Carga la lista de comunas.
        comunas = obtenerComunas(nuevaRegion)
        // Y esto resetea la comuna seleccionada.
        comuna = ""
    }

    fun onComunaSelected(nuevaComuna: String) { comuna = nuevaComuna }
    fun onCodigoDescuentoChange(nuevoCodigo: String) { codigoDescuento = nuevoCodigo }

    fun registrarUsuario() {
        if (nombreCompleto.isBlank() || correo.isBlank() || contrasena.isBlank() || region.isBlank() || comuna.isBlank()) {
            // Opcional: Mostrar Toast de error también
            Toast.makeText(getApplication(), "Hay campos obligatorios vacíos.", Toast.LENGTH_SHORT).show()
            return
        }
        if (contrasena != confirmarContrasena) {
            Toast.makeText(getApplication(), "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show()
            return
        }

        val nuevoUsuario = Usuario(
            nombreCompleto = nombreCompleto,
            fechaNacimiento = fechaNacimiento,
            correo = correo,
            contrasena = contrasena,
            region = region,
            comuna = comuna,
            telefono = telefono.ifBlank { null },
            codigoDescuento = codigoDescuento.ifBlank { null }
        )

        viewModelScope.launch(Dispatchers.IO) { // Usamos Dispatchers.IO para operaciones de base de datos
            repository.insertarUsuario(nuevoUsuario)

            // Para mostrar un Toast, debemos volver al hilo principal (Main)
            launch(Dispatchers.Main) {
                Toast.makeText(getApplication(), "¡Usuario registrado con éxito!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
