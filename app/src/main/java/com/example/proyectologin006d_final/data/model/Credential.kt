package com.example.proyectologin006d_final.data.model

data class Credential(
    val username: String,
    val password: String,
    val role: String,
    val nombre: String
) {
    companion object {
        val Admin = Credential(
            username = "admin@duocuc.cl",
            password = "123456",
            role = "admin",
            nombre = "Administrador"
        )

        val Cliente = Credential(
            username = "cliente@duocuc.cl",
            password = "123456",
            role = "cliente",
            nombre = "Cliente Ejemplo"
        )
    }
}
