package com.example.proyectologin006d_final.ui.registro

data class RegistroUiState(
    val nombreCompleto: String = "",
    val fechaNacimiento: String = "",
    val correo: String = "",
    val contrasena: String = "",
    val confirmarContrasena: String = "",
    val telefono: String = "",
    val region: String = "",
    val comuna: String = "",
    val codigoDescuento: String = "",

    // Campos para los errores de validación
    val errorNombre: String? = null,
    val errorCorreo: String? = null,
    val errorContrasena: String? = null,
    val errorConfirmarContrasena: String? = null,

    // Estado de la operación
    val isLoading: Boolean = false,
    val registroExitoso: Boolean = false
)
