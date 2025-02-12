package com.example.stockcontrol.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockcontrol.model.Items
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ProductViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val mutProduct = MutableLiveData<List<Items>>()
    val products: LiveData<List<Items>> = mutProduct

    init {
        fetchProducts()
    }
    fun fetchProducts() {
        viewModelScope.launch {
            try {
                val db = FirebaseFirestore.getInstance()
                withContext(Dispatchers.IO) {
                    db.collection("products")
                        .get()
                        .addOnSuccessListener { result ->
                            val productList = mutableListOf<Items>()
                            for (document in result) {
                                val product = document.toObject(Items::class.java)  // 🔹 Conversión directa
                                productList.add(product)
                            }
                            mutProduct.postValue(productList)
                        }
                        .addOnFailureListener { exception ->
                            Log.e("FETCH_PRODUCTS", "Error al obtener productos", exception)
                        }
                }
            } catch (e: Exception) {
                Log.e("firebase", "error al traer los items")
            }
        }
    }

    fun insertProduct(item: Items, onSuccess: () -> Unit, onError: (Exception) -> Unit){
        viewModelScope.launch{
            try{
                withContext(Dispatchers.IO){
                    db.collection("products").document(item.id).set(item).await()
                }
                fetchProducts()
                onSuccess()
            }catch(e:Exception){
                onError(e)
            }
        }
    }
    fun deleteProduct(productId: String, context: Context) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    db.collection("products").document(productId).delete().await()
                }
                fetchProducts()
                Toast.makeText(context, "Producto Eliminado", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(context, "Ocurrió un Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getProductById(productId: String): Items? {
        return mutProduct.value?.find { it.id == productId }
    }

}