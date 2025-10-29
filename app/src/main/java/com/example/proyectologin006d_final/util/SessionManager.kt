
package com.example.proyectologin006d_final.util

import android.content.Context
import android.content.SharedPreferences

object SessionManager {

    private const val PREFS_NAME = "LoginPrefs"
    private const val KEY_USERNAME = "username"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    // Guarda el nombre de usuario en SharedPreferences
    fun saveSession(context: Context, username: String) {
        val editor = getPreferences(context).edit()
        editor.putString(KEY_USERNAME, username)
        editor.apply()
    }

    // Obtiene el nombre de usuario guardado. Devuelve null si no hay sesión.
    fun getSession(context: Context): String? {
        return getPreferences(context).getString(KEY_USERNAME, null)
    }

    // Limpia la sesión
    fun clearSession(context: Context) {
        val editor = getPreferences(context).edit()
        editor.clear()
        editor.apply()
    }
}
