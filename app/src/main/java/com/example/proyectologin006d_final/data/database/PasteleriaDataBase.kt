package com.example.proyectologin006d_final.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectologin006d_final.data.dao.PastelDao
import com.example.proyectologin006d_final.data.model.Pastel

@Database(
    entities = [Pastel::class], version = 1
)
abstract class  PastelDatabase : RoomDatabase() {
    abstract fun pastelDao(): PastelDao

    // Lo que viene a continuacion es para que se garantize que solo se cre una instancia en la base de datos
    companion object{
        @Volatile
        private var INSTANCE: PastelDatabase? = null
        fun getDatabase(context: Context): PastelDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                context.applicationContext,
                PastelDatabase::class.java,
                "pastel_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}