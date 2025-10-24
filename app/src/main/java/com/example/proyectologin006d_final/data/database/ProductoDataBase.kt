package com.example.proyectologin006d_final.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectologin006d_final.data.dao.ProductoDao
import com.example.proyectologin006d_final.data.model.Producto



//En esta capa  donde se conecta_todo lo relacionado con la base de datos
@Database(entities = [Producto::class], version = 1)
abstract class ProductoDatabase : RoomDatabase() {

    abstract fun productoDao(): ProductoDao

    companion object {
        @Volatile
        private var INSTANCE: ProductoDatabase? = null

        fun getDatabase(context: Context): ProductoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductoDatabase::class.java,
                    "producto_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
