package com.example.stockcontrol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.stockcontrol.model.Items
import com.example.stockcontrol.repository.ItemRepository
import kotlinx.coroutines.launch


class ProductViewModel(var repository: ItemRepository) : ViewModel() {

    val allProducts: LiveData<List<Items>> = repository.getAllProducts().asLiveData()

    fun insertProduct(product: Items) {
        viewModelScope.launch {
            repository.insertProduct(product)
        }
    }

    fun updateProduct(product: Items) {
        viewModelScope.launch {
            repository.updateProduct(product)
        }
    }

    fun deleteProduct(product: Items) {
        viewModelScope.launch {
            repository.deleteProduct(product.id.toInt())
        }
    }
    fun fetchProducts() {
        viewModelScope.launch {
            repository.fetchProducts()
        }
    }
}