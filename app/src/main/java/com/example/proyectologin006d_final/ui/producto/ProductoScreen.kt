// app/src/main/java/com/example/proyectologin006d_final/ui/producto/ProductsScreen.kt

import com.example.proyectologin006d_final.ui.catalogo.ProductoItem

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
            ProductoItem(producto = producto, cartViewModel = cartViewModel)
        }
    }
}
