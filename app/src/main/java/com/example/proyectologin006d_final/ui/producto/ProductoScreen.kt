package com.example.proyectologin006d_final.ui.producto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.proyectologin006d_final.data.model.Producto
import com.example.proyectologin006d_final.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ProductsScreen() {
   // Vamos a realizar un paso a paso para entenderle capo y para la presentacion no estar perdidos
    // primero se le debe de asignar un fondo para que se mantenga la consistencia de las demas
    val pastelBacground = Color(0xFFFFF8F0)

    // vamos a utilizar un Box para crear un contenedor el cual ocupara nustra pantalla
    Box(
        // el  modifier se usa para manejar  o para cambiarle el comportamiento del composable
        modifier = Modifier
            .fillMaxSize() // el fillMaxSize se usa para que ocupe toda la pantalla
            .background(pastelBacground)
    )
    {
            ProductList()
    }
}
@Composable
fun ProductList(){
    val listarProducto = listOf(
        Producto(
            id = 1,
                codigo = "P001",
                categoria = "Pasteles",
                nombre = "Pastel de Chocolate",
                precio = 12990,
                descripcion = "Sabroso ideal para compartir",
                personalizable = true,
                imagenResId = R.drawable.tcuadrada
        ),
        Producto(
            id = 2,
            codigo = "P001",
            categoria = "Pasteles",
            nombre = "Torta Clasica",
            precio = 12990,
            descripcion = "Lo mejor para empezar, y lo mejor es lo clasico",
            personalizable = true,
            imagenResId = R.drawable.tor_clasic
        ),
        Producto(
            id = 3,
            codigo = "P001",
            categoria = "Pasteles",
            nombre = "Torta Tres Leches",
            precio = 12990,
            descripcion = "La leche ideal para ir al water",
            personalizable = true,
            imagenResId = R.drawable.tortatresleches
        )
    )
    // El lazyColum es un componente para crear listas verticales de la forma mas eficiente
    LazyColumn(
        // el contentPadding se usa para agregar espacio alrededor del contenido del LazyColumn
        contentPadding = PaddingValues(16.dp),
        //El verticalArrangement se usa para organizar los elementos verticalmente en el LazyColumn
        verticalArrangement = Arrangement.spacedBy(16.dp)
    )
    {
        // Los items es la funcion de usar un lazy colum la cual recorre la lista que anterior mente cree
        // por cada producto en la lista se ejecutara en el codigo que esta dentro de las llaves{}
         items(listarProducto){ producto ->
             // por cada producto en la lista se le creara una tarjerta
             ProductCard(producto = producto)
         }
    }
}
// esta funcion es donde estara la de arriba que se envoca
@Composable
fun ProductCard(producto: Producto) {
    // cda producto estara dentro de una tarjeta
    Card(
        // modifier se hace para que la tarjeta ocupe Todo el ancho que le disponjamos\
        modifier = Modifier.fillMaxWidth(),
        // shape se define la forma la cual tendra la tarjeta
        shape = RoundedCornerShape(16.dp),
        // eleveation es la sombra que tendra la tarjeta
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        // color obvio define el color que tendran las tarjetas
        colors = CardDefaults.cardColors(containerColor = Color.White)
    )
    {
        // column apila sus elementos verticalmente encima de otro
        Column {
            Image(
                painter = painterResource(id = producto.imagenResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    , contentScale = ContentScale.Crop
            )
        }
        Column(modifier = Modifier.padding(16.dp)) {
            // 'Text' es el componente para mostrar texto.
            Text(
                // 'text' es el texto a mostrar. Lo sacamos de 'producto.nombre'.
                text = producto.nombre,
                // 'style' le da un estilo predefinido de la tipografía de Material Design.
                style = MaterialTheme.typography.titleLarge,
                // 'fontWeight' define el grosor del texto (negrita).
                fontWeight = FontWeight.Bold
            )
            // 'Spacer' crea un espacio vacío entre componentes.
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = producto.descripcion,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray // Le damos un color gris para diferenciarlo.
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                // Creamos el texto del precio, añadiendo " CLP" al final.
                text = "${producto.precio} CLP",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold, // Un grosor intermedio.
                fontSize = 18.sp, // 'sp' es la unidad para textos, se adapta a la configuración del usuario.
                color = Color(0xFF5D4037) // Un color café para el precio.
            )
        }
    }

}