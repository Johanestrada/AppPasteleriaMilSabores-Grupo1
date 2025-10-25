package com.example.proyectologin006d_final.data.repository

import com.example.proyectologin006d_final.data.dao.ProductoDao
import com.example.proyectologin006d_final.data.model.Producto
import kotlinx.coroutines.flow.Flow
 // en esta capa  el Repository es una capa intermedia BBDD(DAO) y la UI
// encapsulamos las operaciones de las cuales y creamos metodos para cada operacion anteriormente

class ProductoRepository (private val productoDao: ProductoDao){

    suspend fun insertarProducto(producto: Producto){
        productoDao.insertarProducto(producto)
    }

    fun obtenerProductos(): Flow<List<Producto>> {
        return productoDao.obtenerProductos()
    }
    fun obtenerProductoPorId(id: Int): Flow<Producto> {
        return productoDao.obtenerProductoPorId(id)
    }
    suspend fun eliminarProducto(producto: Producto) {
        productoDao.eliminarTodosLosProductos()

    }



}