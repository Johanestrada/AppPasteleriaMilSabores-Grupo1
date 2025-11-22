package com.example.proyectologin006d_final.repository

import com.example.proyectologin006d_final.data.model.Producto
import com.example.proyectologin006d_final.data.remote.ApiService
import com.example.proyectologin006d_final.data.repository.ProductoApiRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest

/**
 * Test unitario para ProductoApiRepository
 * 
 * PROPÓSITO PRINCIPAL:
 * Validar que el repositorio obtiene correctamente los productos desde la API,
 * usando mocks para aislar la prueba de dependencias externas (API real)
 * 
 * - Mock del Repository: Simula el comportamiento del repositorio
 * - Datos de Prueba: Lista predefinida de productos ficticios
 * - Verificación: Confirma que el resultado coincide exactamente con los datos esperados
 */
class ProductoApiRepositoryTest : StringSpec({
    
    "getProductos() debe retornar una lista de productos simulada" {
        // Crea una lista falsa de productos para el test
        val fakeProductos = listOf(
            Producto(id = 1, codigo = "TOR001", categoria = "Tortas", nombre = "Torta Chocolate", precio = 25, descripcion = "Deliciosa torta de chocolate", personalizable = true, imagenResId = 0),
            Producto(id = 2, codigo = "DON001", categoria = "Donuts", nombre = "Donut de Fresa", precio = 5, descripcion = "Donut glaseado con fresa", personalizable = false, imagenResId = 0),
            Producto(id = 3, codigo = "CHE001", categoria = "Tartas", nombre = "Cheesecake", precio = 30, descripcion = "Cheesecake premium", personalizable = true, imagenResId = 0)
        )
        
        // Crea un mock de ProductoApiRepository
        val repo = mockk<ProductoApiRepository>()
        
        // Define el comportamiento del mock: cuando se llame a getProductos(), retornar fakeProductos
        coEvery { repo.getProductos() } returns fakeProductos
        
        // Ejecuta el test en un contexto de corrutinas
        runTest {
            // Llama al método getProductos() del repositorio mockeado
            val result = repo.getProductos()
            
            // Verifica que el resultado contiene exactamente los mismos productos
            result shouldContainExactly fakeProductos
        }
    }
    
    "getProductosDestacados() debe retornar productos destacados" {
        // Crea una lista falsa de productos destacados
        val fakeProductosDestacados = listOf(
            Producto(id = 1, codigo = "TOR001", categoria = "Tortas", nombre = "Torta Chocolate", precio = 25, descripcion = "Deliciosa torta de chocolate", personalizable = true, imagenResId = 0),
            Producto(id = 2, codigo = "DON001", categoria = "Donuts", nombre = "Donut de Fresa", precio = 5, descripcion = "Donut glaseado con fresa", personalizable = false, imagenResId = 0)
        )
        
        // Crea un mock del repositorio
        val repo = mockk<ProductoApiRepository>()
        
        // Define el comportamiento para getProductosDestacados()
        coEvery { repo.getProductosDestacados() } returns fakeProductosDestacados
        
        // Ejecuta el test
        runTest {
            val result = repo.getProductosDestacados()
            result shouldContainExactly fakeProductosDestacados
        }
    }
})
