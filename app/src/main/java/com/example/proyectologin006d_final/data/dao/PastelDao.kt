package com.example.proyectologin006d_final.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.proyectologin006d_final.data.model.Pastel
import kotlinx.coroutines.flow.Flow

@Dao
interface PastelDao {
    //INSERTAR A LA BASE DE DATOS DE ROOM, CREAR UNA TABLA PARA GUARDAR LOS DATOS//
    @Insert
    suspend fun insertarPastel(pastel: Pastel)
    //CONSULTAR LA BASE DE DATOS DE ROOM//
    @Query("SELECT * FROM pasteles")
    fun obtenerPasteles(): Flow<List<Pastel>>
    // Eliminar Pasteles
    @Delete
    suspend fun eliminarproducto(pastel: Pastel)
}