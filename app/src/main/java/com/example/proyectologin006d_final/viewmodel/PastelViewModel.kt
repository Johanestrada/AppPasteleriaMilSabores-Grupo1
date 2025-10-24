package com.example.proyectologin006d_final.viewmodelimport

import androidx.lifecycle.ViewModel

import com.example.proyectologin006d_final.data.model.Pastel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope


class PastelViewModel : ViewModel() {
    private val _pasteles = MutableStateFlow<List<Pastel>>(emptyList())

    val pasteles: StateFlow<List<Pastel>> = _pasteles.asStateFlow()
}
fun cargarPasteles( pastel: Pastel){

}