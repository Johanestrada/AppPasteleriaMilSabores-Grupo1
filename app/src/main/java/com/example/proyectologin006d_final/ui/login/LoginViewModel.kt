package com.example.proyectologin006d_final.ui.login

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.proyectologin006d_final.data.repository.AuthRepository

class LoginViewModel(
    private val repo: AuthRepository = AuthRepository()
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onCorreoChange(value: String) {
        uiState = uiState.copy(correo = value, mensaje = "")
    }

    fun onClaveChange(value: String) {
        uiState = uiState.copy(clave = value, mensaje = "")
    }

    fun submit(onSuccess: (String, String) -> Unit) {
        uiState = uiState.copy(isLoading = true, mensaje = "")

        val user = repo.login(
            uiState.correo.trim(),
            uiState.clave
        )

        uiState = uiState.copy(isLoading = false)

        if (user != null) {
            // Si se encuentra usuario, pasa rol y nombre
            onSuccess(user.role, user.nombre)
        } else {
            uiState = uiState.copy(mensaje = "Correo o clave incorrectos")
        }
    }
}
