package com.example.proyectologin006d_final.ui.perfil

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectologin006d_final.data.database.AppDataBase
import com.example.proyectologin006d_final.data.model.Usuario
import com.example.proyectologin006d_final.data.repository.UsuarioRepository
import com.example.proyectologin006d_final.util.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PerfilViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UsuarioRepository

    private val _user = MutableStateFlow<Usuario?>(null)
    val user: StateFlow<Usuario?> = _user.asStateFlow()

    init {
        val usuarioDao = AppDataBase.getDatabase(application).usuarioDao()
        repository = UsuarioRepository(usuarioDao)
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            val userEmail = SessionManager.getUserEmail(getApplication())
            userEmail?.let {
                repository.usuarioDao.obtenerUsuarioPorCorreo(it).collect {
                    _user.value = it
                }
            }
        }
    }
}