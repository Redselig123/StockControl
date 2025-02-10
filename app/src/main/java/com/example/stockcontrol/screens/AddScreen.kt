package com.example.stockcontrol.screens

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AddProduct() {
    var id by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
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
            modifier = Modifier.padding(top = 64.dp)
        )
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f) // Reduce el ancho para mejor apariencia
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center, // Centra verticalmente
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputRow(label = "ID:", value = id, onValueChange = { id = it })
                InputRow(label = "PRECIO:", value = price, onValueChange = { price = it })
                InputRow(
                    label = "DESCRIPCIÓN:",
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier.height(120.dp) // Ajustar altura
                )
                InputRow(label = "STOCK:", value = stock, onValueChange = { stock = it })
                Button(onClick = {}) {///to room and firebase
                    Text("AGREGAR")
                }
            }
        }
    }
}
@Composable
fun InputRow(label: String, value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, modifier = Modifier.width(100.dp)) // Ancho fijo para alinear etiquetas
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text("Ingrese $label") },
            modifier = Modifier.weight(1f).then(modifier) // Igual ancho para todos los campos
        )
    }
}
