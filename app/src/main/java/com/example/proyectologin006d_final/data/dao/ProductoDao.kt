package com.example.proyectologin006d_final.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proyectologin006d_final.data.model.Producto
import kotlinx.coroutines.flow.Flow

@Dao

interface ProductoDao{
    @Insert
    suspend fun insertarProducto(producto: Producto)

    @Query("SELECT * FROM productos")
    fun obtenerProductos(): Flow<List<Producto>>

    @Query("SELECT * FROM productos WHERE id = :id")
    fun obtenerProductoPorId(id: Int): Flow<Producto>

    @Query("DELETE FROM productos WHERE id = :id")
    suspend fun eliminarProductoPorId(id: Int)

    @Query("DELETE FROM productos")
    suspend fun eliminarTodosLosProductos()

}