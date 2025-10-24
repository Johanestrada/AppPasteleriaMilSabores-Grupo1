package com.example.proyectologin006d_final.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pasteles")
data class Pastel (
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val categoria: String,
    val imagenUrl: String
)