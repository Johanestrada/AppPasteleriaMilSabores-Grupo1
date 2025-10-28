package com.example.proyectologin006d_final.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectologin006d_final.data.dao.ProductoDao
import com.example.proyectologin006d_final.data.dao.UsuarioDao
import com.example.proyectologin006d_final.data.model.Producto
import com.example.proyectologin006d_final.data.model.Usuario

@Database(entities = [Producto::class, Usuario::class], version = 2, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun productoDao(): ProductoDao
    abstract fun usuarioDao(): UsuarioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_database"
                )
                    // Permite a Room recrear las tablas si se actualiza la versión de la BD.
                    // Muy útil para desarrollo.
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
