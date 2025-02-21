package com.example.stockcontrol.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.stockcontrol.model.Items
import kotlinx.coroutines.flow.Flow
@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Items)

    @Update
    suspend fun update(product: Items)

    @Delete
    suspend fun delete(product : Items)

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<Items>>

    @Query("DELETE FROM products WHERE id = :productId")
    suspend fun deleteById(productId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<Items>)
}