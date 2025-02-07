package com.example.stockcontrol


import android.os.Bundle

import androidx.activity.ComponentActivity

import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.sp

import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockcontrol.screens.HomeScreen
import com.example.stockcontrol.screens.LoginScreen

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
                    composable("home") { HomeScreen() }
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