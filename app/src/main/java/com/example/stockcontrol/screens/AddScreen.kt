package com.example.stockcontrol.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockcontrol.model.Items
import com.example.stockcontrol.viewModel.ProductViewModel


@Composable
fun AddProduct(navController: NavController, productViewModel: ProductViewModel) {
    var id by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // Usar el fondo del tema
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "NUEVO PRODUCTO",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 64.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    InputRow(label = "ID:", value = id, onValueChange = { id = it })
                    InputRow(
                        label = "DESCRIPCIÓN:",
                        value = description,
                        onValueChange = { description = it },
                        modifier = Modifier.height(120.dp)
                    )
                    InputRow(label = "PRECIO:", value = price, onValueChange = { price = it })

                    InputRow(label = "STOCK:", value = stock, onValueChange = { stock = it })
                    Button(onClick = {
                        if (id.isNotBlank() && price.isNotBlank() && description.isNotBlank() && stock.isNotBlank()) {
                            val priceValue = price.toDoubleOrNull()
                            val stockValue = stock.toIntOrNull()
                            if (priceValue != null && stockValue != null && priceValue > 0 && stockValue >= 0) {
                                val product =
                                    Items(id, description, price.toDouble(), stock.toInt())
                                productViewModel.insertProduct(
                                    product,
                                    onSuccess = {},
                                    onError = {})
                                Toast.makeText(context, "Producto Agregado", Toast.LENGTH_SHORT)
                                    .show()
                                navController.navigate("home")
                            } else {
                                Toast.makeText(
                                    context,
                                    "Precio y Stock deben ser mayores a 0",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Todos los campos son obligatorios",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }) {
                        Text("AGREGAR", color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputRow(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            label,
            modifier = Modifier.width(120.dp),
            color = MaterialTheme.colorScheme.onSurface
        ) // Ancho fijo para alinear etiquetas
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {  },
            modifier = Modifier.weight(1f).then(modifier),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary, // Color del borde cuando está enfocado
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary, // Color del borde cuando no está enfocado
                focusedLabelColor = MaterialTheme.colorScheme.primary, // Color de la etiqueta cuando está enfocado
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurface, // Color de la etiqueta cuando no está enfocado
                focusedTextColor = MaterialTheme.colorScheme.onBackground, // Color del texto cuando está enfocado
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}