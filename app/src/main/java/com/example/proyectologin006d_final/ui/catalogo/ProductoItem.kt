package com.example.proyectologin006d_final.ui.catalogo

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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

@Composable
fun ProductoItem(producto: Producto, cartViewModel: CartViewModel) {
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