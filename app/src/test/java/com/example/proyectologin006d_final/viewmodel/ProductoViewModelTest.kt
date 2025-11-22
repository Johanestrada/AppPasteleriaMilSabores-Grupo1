package com.example.proyectologin006d_final.viewmodel

import com.example.proyectologin006d_final.data.model.Producto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test unitario para ProductoViewModel
 * 
 * PROPÓSITO PRINCIPAL:
 * Validar que el ViewModel maneja correctamente el estado de los productos
 * a través de su StateFlow, simulando el flujo de datos desde el repositorio
 * 
 * - Configuración de Corrutinas: Usa UnconfinedTestDispatcher para ejecución inmediata
 * - ViewModel Real: Testea la instancia real de ProductoViewModel (no un mock)
 * - Manipulación Directa: Asigna datos fake directamente al StateFlow interno
 * - Verificaciones de Estado: Confirma que los datos se reflejan correctamente
 */
@OptIn(ExperimentalCoroutinesApi::class)
class ProductoViewModelTest {
    
    // Crea un dispatcher de test sin confinamiento para ejecución inmediata
    private val testDispatcher = UnconfinedTestDispatcher()
    
    // Método que se ejecuta antes de cada test
    @BeforeEach
    fun setUp() {
        // Establece el dispatcher principal para tests
        Dispatchers.setMain(testDispatcher)
    }
    
    // Método que se ejecuta después de cada test
    @AfterEach
    fun tearDown() {
        // Restaura el dispatcher principal original
        Dispatchers.resetMain()
    }
    
    /**
     * Test que verifica que el ViewModel puede almacenar productos correctamente
     */
    @Test
    fun `guardarProducto debe agregar un producto a la lista`() = runTest(testDispatcher) {
        // Crea una instancia del ViewModel bajo test
        val viewModel = ProductoViewModel()
        
        // Crea un producto de prueba
        val productoTest = Producto(
            id = 1,
            codigo = "TOR001",
            categoria = "Tortas",
            nombre = "Torta de Chocolate",
            precio = 25,
            descripcion = "Deliciosa torta",
            personalizable = true,
            imagenResId = 0
        )
        
        // Verifica que inicialmente la lista está vacía
        assertEquals(0, viewModel.productos.value.size, "La lista debe estar vacía al inicio")
        
        // Llama al método para guardar un producto
        viewModel.guardarProducto(productoTest)
        
        // Verifica que el producto fue agregado
        assertEquals(1, viewModel.productos.value.size, "Debe haber un producto en la lista")
        assertEquals(productoTest.nombre, viewModel.productos.value[0].nombre, "El nombre debe coincidir")
    }
    
    /**
     * Test que verifica que se pueden guardar múltiples productos
     */
    @Test
    fun `guardarProducto debe agregar múltiples productos en orden`() = runTest(testDispatcher) {
        val viewModel = ProductoViewModel()
        
        // Crea varios productos de prueba
        val producto1 = Producto(id = 1, codigo = "TOR001", categoria = "Tortas", nombre = "Torta", precio = 25, descripcion = "Torta", personalizable = true, imagenResId = 0)
        val producto2 = Producto(id = 2, codigo = "DON001", categoria = "Donuts", nombre = "Donut", precio = 5, descripcion = "Donut", personalizable = false, imagenResId = 0)
        val producto3 = Producto(id = 3, codigo = "CHE001", categoria = "Tartas", nombre = "Cheesecake", precio = 30, descripcion = "Cheesecake", personalizable = true, imagenResId = 0)
        
        // Agrega los productos
        viewModel.guardarProducto(producto1)
        viewModel.guardarProducto(producto2)
        viewModel.guardarProducto(producto3)
        
        // Verifica que hay 3 productos
        assertEquals(3, viewModel.productos.value.size, "Debe haber 3 productos")
        
        // Verifica que están en el orden correcto
        assertEquals("Torta", viewModel.productos.value[0].nombre)
        assertEquals("Donut", viewModel.productos.value[1].nombre)
        assertEquals("Cheesecake", viewModel.productos.value[2].nombre)
    }
    
    /**
     * Test que verifica la integridad de los datos almacenados
     */
    @Test
    fun `los datos del producto deben mantenerse íntegros`() = runTest(testDispatcher) {
        val viewModel = ProductoViewModel()
        
        val productoTest = Producto(
            id = 42,
            codigo = "BRO001",
            categoria = "Postres",
            nombre = "Brownie Premium",
            precio = 15,
            descripcion = "Brownie de chocolate premium",
            personalizable = false,
            imagenResId = 0
        )
        
        viewModel.guardarProducto(productoTest)
        
        val productoAlmacenado = viewModel.productos.value[0]
        
        assertEquals(42, productoAlmacenado.id)
        assertEquals("BRO001", productoAlmacenado.codigo)
        assertEquals("Postres", productoAlmacenado.categoria)
        assertEquals("Brownie Premium", productoAlmacenado.nombre)
        assertEquals(15, productoAlmacenado.precio)
        assertEquals("Brownie de chocolate premium", productoAlmacenado.descripcion)
        assertEquals(false, productoAlmacenado.personalizable)
    }
}
