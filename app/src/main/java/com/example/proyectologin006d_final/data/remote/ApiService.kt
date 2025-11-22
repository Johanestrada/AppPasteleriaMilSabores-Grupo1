package com.example.proyectologin006d_final.data.remote

import com.example.proyectologin006d_final.data.model.Producto
import retrofit2.http.GET

/**
 * Interfaz que define los endpoints disponibles de la API REST
 * para obtener productos de la pastelería
 */
interface ApiService {
    
    /**
     * Obtiene la lista de todos los productos disponibles
     * @return Lista de productos desde la API
     */
    @GET("/productos")
    suspend fun getProductos(): List<Producto>
    
    /**
     * Obtiene los productos destacados o en promoción
     * @return Lista de productos destacados
     */
    @GET("/productos/destacados")
    suspend fun getProductosDestacados(): List<Producto>
}
