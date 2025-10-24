package com.example.proyectologin006d_final.data.repository

import com.example.proyectologin006d_final.data.dao.PastelDao
import com.example.proyectologin006d_final.data.model.Pastel
import kotlinx.coroutines.flow.Flow

class PastelRepository(private val pastelDao: PastelDao) {
    // El repositorio encapsula la logica de acesso a datos, en el luhar que el viewModel con el dao , se hace mejor por el repositorio

    suspend fun  insertPastel(pastel: Pastel){
        pastelDao.insertarPastel(pastel)
    }
    fun obtenerPastel(): Flow<List<Pastel>> {
        return pastelDao.obtenerPasteles()
    }
    suspend fun eliminarPastel(pastel: Pastel){
        pastelDao.eliminarproducto(pastel)
    }
}