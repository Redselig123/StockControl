package com.example.stockcontrol


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockcontrol.screens.AddProduct
import com.example.stockcontrol.screens.HomeScreen
import com.example.stockcontrol.screens.Item
import com.example.stockcontrol.screens.LoginScreen
import com.example.stockcontrol.screens.ProductDetailScreen

import com.example.stockcontrol.ui.theme.StockControlTheme
import com.example.stockcontrol.viewModel.AuthViewModel


class MainActivity : ComponentActivity() {
    val GOOGLE_SIGN_IN_REQUEST = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StockControlTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        val authViewModel: AuthViewModel = viewModel()
                        LoginScreen(navController, authViewModel)
                    }
                    composable("home") {
                        HomeScreen(items = listOf(
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
                            onAddClick = {navController.navigate("AddProduct")}, onItemClick = {navController.navigate("productScreen")}
                        )
                    }
                    composable("AddProduct"){
                        AddProduct(navController = navController)
                    }
                    composable("productScreen"){
                        ProductDetailScreen(Item("1", "Producto A", 10.99, 5), onUpdateClick = {}, onDeleteClick = {}, navController = navController)
                    }
                }
            }
        }
        /* @Composable
     fun nav(d: String) {
         val navController = rememberNavController()
         navController.navigate(d)
     }*/
    }
}