package com.example.proyectologin006d_final.data.repository

import com.example.proyectologin006d_final.data.model.Credential

class AuthRepository(
    private val credentials: List<Credential> = listOf(
        Credential.Admin,
        Credential.Cliente
    )
) {
    fun login(correo: String, clave: String): Credential? {
        return credentials.find {
            it.username == correo && it.password == clave
        }
    }
}
