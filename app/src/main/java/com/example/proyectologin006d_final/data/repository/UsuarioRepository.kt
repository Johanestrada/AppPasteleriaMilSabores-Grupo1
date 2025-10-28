package com.example.proyectologin006d_final.data.repository

import com.example.proyectologin006d_final.data.dao.UsuarioDao
import com.example.proyectologin006d_final.data.model.Usuario

class UsuarioRepository(private val usuarioDao: UsuarioDao) {
    suspend fun insertarUsuario(usuario: Usuario) {
        usuarioDao.insertarUsuario(usuario)
    }

    suspend fun buscarUsuario(correo: String, contrasena: String): Usuario? {
        return usuarioDao.findUserByEmailAndPassword(correo, contrasena)
    }
}
