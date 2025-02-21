package com.example.stockcontrol.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Items(
    @PrimaryKey(autoGenerate = true)val id: Int = 0,
    val description: String = "",
    var price: Double = 0.0,
    var stock: Int = 0
)

