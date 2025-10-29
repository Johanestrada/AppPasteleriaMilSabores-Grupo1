// Archivo: ui/login/LoginViewModel.kt
package com.example.proyectologin006d_final.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.proyectologin006d_final.data.database.AppDataBase
import com.example.proyectologin006d_final.data.repository.UsuarioRepository
import com.example.proyectologin006d_final.util.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UsuarioRepository

    init {
        val usuarioDao = AppDataBase.getDatabase(application).usuarioDao()
        repository = UsuarioRepository(usuarioDao)
    }

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onCorreoChange(value: String) {
        uiState = uiState.copy(correo = value, mensaje = "")
    }

    fun onClaveChange(value: String) {
        uiState = uiState.copy(clave = value, mensaje = "")
    }

    // ✨ LÓGICA DE LOGIN CON ROOM ✨
    fun submit(onSuccess: (String) -> Unit) {
        uiState = uiState.copy(isLoading = true, mensaje = "")

        viewModelScope.launch(Dispatchers.IO) {
            val user = repository.buscarUsuario(
                uiState.correo.trim(),
                uiState.clave
            )

            launch(Dispatchers.Main) {
                uiState = uiState.copy(isLoading = false)
                if (user != null) {
                    // Guardamos la sesión del usuario
                    SessionManager.saveSession(getApplication(), user.nombreCompleto)
                    // Si encontramos el usuario, navegamos. Pasamos el nombre completo.
                    onSuccess(user.nombreCompleto)
                } else {
                    uiState = uiState.copy(mensaje = "Correo o clave incorrectos")
                }
            }
        }
    }
}
