package com.example.proyectologin006d_final.data.repository

import com.example.proyectologin006d_final.data.model.Producto
import com.example.proyectologin006d_final.data.remote.RetrofitInstance

/**
 * Repositorio que actúa como capa intermedia entre la API remota y el resto de la aplicación
 * Se encarga de obtener los productos desde la API REST usando Retrofit
 */
class ProductoApiRepository {
    
    /**
     * Obtiene la lista de todos los productos desde la API
     * @return Lista de productos obtenidos de la API
     * @throws Exception si ocurre un error en la llamada a la API
     */
    suspend fun getProductos(): List<Producto> {
        return RetrofitInstance.api.getProductos()
    }
    
    /**
     * Obtiene los productos destacados o en promoción desde la API
     * @return Lista de productos destacados
     * @throws Exception si ocurre un error en la llamada a la API
     */
    suspend fun getProductosDestacados(): List<Producto> {
        return RetrofitInstance.api.getProductosDestacados()
    }
}
