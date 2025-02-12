package com.example.stockcontrol


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stockcontrol.model.Items
import com.example.stockcontrol.screens.AddProduct
import com.example.stockcontrol.screens.HomeScreen
import com.example.stockcontrol.screens.LoginScreen
import com.example.stockcontrol.screens.ProductDetailScreen
import androidx.compose.material3.Text
import com.example.stockcontrol.ui.theme.StockControlTheme
import com.example.stockcontrol.viewModel.AuthViewModel
import com.example.stockcontrol.viewModel.ProductViewModel


class MainActivity : ComponentActivity() {
    val GOOGLE_SIGN_IN_REQUEST = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StockControlTheme {
                val productViewModel: ProductViewModel = viewModel()
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "login") {

                    composable("login") {
                        val authViewModel: AuthViewModel = viewModel()
                        LoginScreen(navController, authViewModel)
                    }
                    composable("home") {
                        HomeScreen(
                            onAddClick = { navController.navigate("AddProduct") },
                            onItemClick = {productId ->
                                navController.navigate("productScreen/${productId}")
                            },
                            productViewModel
                        )
                    }
                    composable("AddProduct") {
                        AddProduct(navController = navController, productViewModel)
                    }
                    composable(
                        "productScreen/{productId}",
                        arguments = listOf(navArgument("productId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val productId = backStackEntry.arguments?.getString("productId")
                        if (productId != null) {
                            val product = productViewModel.getProductById(productId)
                            if (product != null) {
                                ProductDetailScreen(
                                    product = product,
                                    onUpdateClick = {},
                                    navController = navController,
                                    productViewModel = productViewModel
                                )
                            } else {
                                // Manejar el caso donde el producto no se encuentra
                                Text("Producto no encontrado")
                            }
                        } else {
                            // Manejar el caso donde el productId es nulo
                            Text("ID de producto inválido")
                        }
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