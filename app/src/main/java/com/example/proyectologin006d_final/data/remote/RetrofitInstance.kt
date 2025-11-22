package com.example.proyectologin006d_final.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton que proporciona una instancia única de Retrofit
 * configurada con la URL base de la API de la pastelería
 */
object RetrofitInstance {
    
    // URL base de la API (cambiar según tu servidor backend)
    private const val BASE_URL = "https://api.pasteleriamiIsabores.com/"
    
    /**
     * Instancia lazy de ApiService que se crea una sola vez
     */
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
