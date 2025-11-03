// app/src/main/java/com/example/proyectologin006d_final/ui/producto/ProductsScreen.kt

package com.example.proyectologin006d_final.ui.producto

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectologin006d_final.R
import com.example.proyectologin006d_final.data.model.Producto
import com.example.proyectologin006d_final.viewmodel.CartViewModel

// La pantalla ahora recibe el ViewModel como parámetro
@Composable
fun ProductsScreen(cartViewModel: CartViewModel) {
    val pastelBackground = Color(0xFFFFF8F0)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(pastelBackground)
    ) {
        // Pasamos el ViewModel a la lista de productos
        ProductList(cartViewModel = cartViewModel)
    }
}

@Composable
fun ProductList(cartViewModel: CartViewModel) {
    val listarProducto = listOf(
        // ... (tu lista de productos no cambia)
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
        ),
        Producto(
            id = 4, codigo = "P004", categoria = "Alfajores", nombre = "Alfajor de Chocolate",
            precio = 6990, descripcion = "Lo cremoso esta dentro del manjar", personalizable = true,
            imagenResId = R.drawable.alfajor
        ),
        Producto(
            id = 5, codigo = "P005", categoria = "El mas Grande", nombre = "Torta Colo Colo",
            precio = 6990, descripcion = "Torta De el Mas grande", personalizable = true,
            imagenResId = R.drawable.futbol
        ),
        Producto(
            id = 6, codigo = "P006", categoria = "Tortas", nombre = "Torta Cars",
            precio = 6990, descripcion = "Torta Cars", personalizable = true,
            imagenResId = R.drawable.cars
        ),
        Producto(
            id = 7, codigo = "P007", categoria = "Torta ", nombre = "Torta Animada",
            precio = 6990, descripcion = "Torta Animada", personalizable = true,
            imagenResId = R.drawable.pastelpzl
        )
    )
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(listarProducto) { producto ->
            // Pasamos el producto y el ViewModel a cada tarjeta
            ProductCard(producto = producto, cartViewModel = cartViewModel)
        }
    }
}

@Composable
fun ProductCard(producto: Producto, cartViewModel: CartViewModel) {
    val context = LocalContext.current // Necesario para mostrar el Toast

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Image(
                painter = painterResource(id = producto.imagenResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier.padding(16.dp)) {
                Column {
                    Text(text = producto.nombre, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = producto.descripcion, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${producto.precio} CLP", style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold, fontSize = 18.sp, color = Color(0xFF5D4037)
                    )
                }

                IconButton(
                    onClick = {
                        // AQUÍ ESTÁ LA LÓGICA
                        // Llamamos a la función del ViewModel para añadir el producto
                        cartViewModel.addToCart(producto)
                        // Mostramos un mensaje de confirmación
                        Toast.makeText(context, "${producto.nombre} añadido al carrito", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Añadir al carrito",
                        tint = Color(0xFF5D4037)
                    )
                }
            }
        }
    }
}

