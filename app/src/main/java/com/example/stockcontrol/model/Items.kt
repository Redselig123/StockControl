package com.example.stockcontrol.model

data class Items(val id: String = "",
                 val description: String = "",
                 var price: Double = 0.0,
                 var stock: Int = 0
)

