package com.example.proyectologin006d_final.ui.login

data class LoginUiState(
    val correo: String = "",
    val clave: String = "",
    val mensaje: String = "",
    val isLoading: Boolean = false
)
