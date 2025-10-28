package com.example.proyectologin006d_final.ui.registro

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color // ¡Importante importar Color!
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectologin006d_final.viewmodel.RegistroViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class) // Necesario para Scaffold y TopAppBar
@Composable
fun RegistroScreen(
    navController: NavController,
    registroViewModel: RegistroViewModel = viewModel()
) {
    // 1. Se definen los mismos colores pastel que en LoginScreen
    val pastelBackground = Color(0xFFFFF8F0)
    val pastelAccent = Color(0xFFFFCCBC)
    val pastelText = Color(0xFF5D4037)

    // 2. Se crea un MaterialTheme local, igual que en LoginScreen
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = pastelAccent,
            onPrimary = Color.White,
            background = pastelBackground,
            surface = pastelBackground,
            onSurface = pastelText
        )
    ) {
        // 3. Se usa Scaffold para una estructura consistente con barra superior
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Formulario de Registro",
                            color = MaterialTheme.colorScheme.onPrimary // Usa el color del tema
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = pastelAccent)
                )
            }
            // El color de fondo se toma automáticamente del 'background' del colorScheme
        ) { innerPadding ->
            // La Column ahora usa el padding proporcionado por el Scaffold
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding) // Evita que el contenido quede debajo de la TopBar
                    .padding(horizontal = 16.dp) // Padding lateral para los campos
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(24.dp)) // Espacio después de la TopBar

                // --- Campos del Formulario (sin cambios en su lógica) ---

                OutlinedTextField(
                    value = registroViewModel.nombreCompleto,
                    onValueChange = { registroViewModel.onNombreChange(it) },
                    label = { Text("Nombre Completo") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))

                DatePickerField(
                    fechaSeleccionada = registroViewModel.fechaNacimiento,
                    onFechaSeleccionada = { registroViewModel.onFechaNacimientoChange(it) }
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = registroViewModel.correo,
                    onValueChange = { registroViewModel.onCorreoChange(it) },
                    label = { Text("Correo Electrónico") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = registroViewModel.contrasena,
                    onValueChange = { registroViewModel.onContrasenaChange(it) },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = registroViewModel.confirmarContrasena,
                    onValueChange = { registroViewModel.onConfirmarContrasenaChange(it) },
                    label = { Text("Confirmar Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    isError = registroViewModel.contrasena != registroViewModel.confirmarContrasena && registroViewModel.confirmarContrasena.isNotBlank()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = registroViewModel.telefono,
                    onValueChange = { registroViewModel.onTelefonoChange(it) },
                    label = { Text("Teléfono (Opcional)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))

                RegionComunaDropDowns(viewModel = registroViewModel)
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = registroViewModel.codigoDescuento,
                    onValueChange = { registroViewModel.onCodigoDescuentoChange(it) },
                    label = { Text("Código de Descuento (Opcional)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        registroViewModel.registrarUsuario()
                        // Opcional: navegar hacia atrás o mostrar un mensaje
                        // navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = registroViewModel.nombreCompleto.isNotBlank() &&
                            registroViewModel.correo.isNotBlank() &&
                            registroViewModel.contrasena.isNotBlank() &&
                            registroViewModel.region.isNotBlank() &&
                            registroViewModel.comuna.isNotBlank() &&
                            registroViewModel.contrasena == registroViewModel.confirmarContrasena,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = pastelAccent // Usa el color pastel para el botón
                    )
                ) {
                    Text("Registrarse")
                }
                Spacer(modifier = Modifier.height(16.dp)) // Espacio al final
            }
        }
    }
}

// ... El resto del archivo (DatePickerField, RegionComunaDropDowns y el Preview) no necesita cambios ...

@Composable
fun DatePickerField(
    fechaSeleccionada: String,
    onFechaSeleccionada: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val monthFormatted = month + 1
            onFechaSeleccionada("$dayOfMonth/$monthFormatted/$year")
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    OutlinedTextField(
        value = fechaSeleccionada,
        onValueChange = {},
        label = { Text("Fecha de Nacimiento") },
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { datePickerDialog.show() },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Seleccionar Fecha",
                modifier = Modifier.clickable { datePickerDialog.show() }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegionComunaDropDowns(viewModel: RegistroViewModel) {
    var regionExpanded by remember { mutableStateOf(false) }
    var comunaExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = regionExpanded,
        onExpandedChange = { regionExpanded = !regionExpanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = viewModel.region,
            onValueChange = {},
            readOnly = true,
            label = { Text("Región") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = regionExpanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = regionExpanded,
            onDismissRequest = { regionExpanded = false }
        ) {
            viewModel.regiones.forEach { regionSeleccionada ->
                DropdownMenuItem(
                    text = { Text(regionSeleccionada) },
                    onClick = {
                        viewModel.onRegionSelected(regionSeleccionada)
                        regionExpanded = false
                    }
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(8.dp))

    ExposedDropdownMenuBox(
        expanded = comunaExpanded,
        onExpandedChange = {
            if (viewModel.comunas.isNotEmpty()) {
                comunaExpanded = !comunaExpanded
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = viewModel.comuna,
            onValueChange = {},
            readOnly = true,
            label = { Text("Comuna") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = comunaExpanded) },
            enabled = viewModel.comunas.isNotEmpty(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = comunaExpanded,
            onDismissRequest = { comunaExpanded = false }
        ) {
            viewModel.comunas.forEach { comunaSeleccionada ->
                DropdownMenuItem(
                    text = { Text(comunaSeleccionada) },
                    onClick = {
                        viewModel.onComunaSelected(comunaSeleccionada)
                        comunaExpanded = false
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegistroScreenPreview() {
    RegistroScreen(navController = rememberNavController())
}
