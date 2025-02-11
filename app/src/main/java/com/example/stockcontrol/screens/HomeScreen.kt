package com.example.stockcontrol.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Item(val id: String, val description: String, val price: Double, val stock: Int)
@Composable
fun HomeScreen(items: List<Item>, onAddClick: () -> Unit, onItemClick: (Item) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    //traer de la data base la lista al cargar la pantalla por dbViewModel. trae una lista de tipo Item
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },//to room and firebase
                label = { Text("Buscar") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onAddClick) {
                Text("AGREGAR")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text("ID", modifier = Modifier.weight(1f), fontSize = 10.sp)
            Text("DESCRIPCIÓN", modifier = Modifier.weight(1f), fontSize = 10.sp)
            Text("PRECIO", modifier = Modifier.weight(1f), fontSize = 10.sp)
            Text("STOCK", modifier = Modifier.weight(1f), fontSize = 10.sp)
        }

        LazyColumn {
            items(items) { item ->
                ItemRow(item, onItemClick)
            }
        }
    }
}

@Composable
fun ItemRow(item: Item, onItemClick: (Item) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onItemClick(item) }//to room and firebase and product_screen
    ) {
        Text(item.id, modifier = Modifier.weight(1f), fontSize = 10.sp)
        Text(item.description, modifier = Modifier.weight(1f), fontSize = 10.sp)
        Text(item.price.toString(), modifier = Modifier.weight(1f), fontSize = 10.sp)
        Text(item.stock.toString(), modifier = Modifier.weight(1f), fontSize = 10.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchScreen() {
    HomeScreen(
        items = listOf(
            Item("1", "Producto A", 10.99, 5),
            Item("2", "Producto B", 20.50, 3),
            Item("3", "Producto B", 20.50, 3),
            Item("4", "Producto B", 20.50, 3),
            Item("5", "Producto B", 20.50, 3),
            Item("6", "Producto B", 20.50, 3),
            Item("7", "Producto B", 20.50, 3),
            Item("8", "Producto B", 20.50, 3),
            Item("9", "Producto B", 20.50, 3),
            Item("10", "Producto B", 20.50, 3),
            Item("11", "Producto B", 20.50, 3),
        ),
        onAddClick = {}, onItemClick = {}
    )
}
