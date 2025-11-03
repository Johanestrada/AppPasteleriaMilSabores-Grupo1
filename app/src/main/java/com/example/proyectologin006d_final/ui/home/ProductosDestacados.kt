package com.example.proyectologin006d_final.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectologin006d_final.R
import com.example.proyectologin006d_final.data.model.Producto
import com.example.proyectologin006d_final.ui.catalogo.ProductoItem
import com.example.proyectologin006d_final.viewmodel.CartViewModel

@Composable
fun ProductosDestacados(navController: NavController, cartViewModel: CartViewModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Productos Destacados",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
        )
        val productosDestacados = listOf(
            Producto(
                id = 1, codigo = "P001", categoria = "Pasteles", nombre = "Pastel de Chocolate",
                precio = 12990, descripcion = "Sabroso ideal para compartir", personalizable = true,
                imagenResId = R.drawable.tcuadrada
            ),
            Producto(
                id = 2, codigo = "P002", categoria = "Pasteles", nombre = "Torta Clasica",
                precio = 12990, descripcion = "Lo mejor para empezar, y lo mejor es lo clasico", personalizable = true,
                imagenResId = R.drawable.tor_clasic
            ),
            Producto(
                id = 3, codigo = "P003", categoria = "Pasteles", nombre = "Torta Tres Leches",
                precio = 12990, descripcion = "La leche ideal para ir al water", personalizable = true,
                imagenResId = R.drawable.tortatresleches
            )
        )
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(productosDestacados) { producto ->
                ProductoItem(producto = producto, cartViewModel = cartViewModel)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}