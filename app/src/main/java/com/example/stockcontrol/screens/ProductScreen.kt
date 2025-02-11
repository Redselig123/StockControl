package com.example.stockcontrol.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProductDetailScreen(
    product: Item,
    onUpdateClick: (Item) -> Unit,
    onDeleteClick: (String) -> Unit,
    navController: NavController
) {

    var stock by rememberSaveable { mutableStateOf(product.stock.toString()) }
    var price by rememberSaveable { mutableStateOf(product.price.toString()) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Detalle del Producto",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = product.id,
            onValueChange = {},
            label = { Text("ID") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))


        OutlinedTextField(
            value = product.description,
            onValueChange = {},
            label = { Text("Descripción") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))


        OutlinedTextField(
            value = stock,
            onValueChange = { stock = it },
            label = { Text("Stock") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))


        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Precio") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {///if not empty
                onUpdateClick(product.copy(stock = stock.toIntOrNull() ?: product.stock, price = price.toDoubleOrNull() ?: product.price))
                Toast.makeText(context, "Producto actualizado", Toast.LENGTH_SHORT).show()
                navController.navigate("home")
            }) {
                Text("Actualizar")
            }

            Button(
                onClick = {
                    onDeleteClick(product.id)
                    Toast.makeText(context, "Producto Eliminado", Toast.LENGTH_SHORT).show()

                    navController.navigate("home")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Eliminar")
            }
        }
    }
}