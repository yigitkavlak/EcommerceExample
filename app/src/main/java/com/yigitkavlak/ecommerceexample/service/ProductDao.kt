package com.yigitkavlak.ecommerceexample.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yigitkavlak.ecommerceexample.model.Products

@Dao
interface ProductDao {

    @Insert
    suspend fun insertAll(vararg products: Products): List<Long>

    //Insert -> INSERT INTO
    //suspend -> coroutine, pause and resume
    //vararg -> multiple products objects
    //List<Long> -> primary keys

    @Query("SELECT * FROM  products")
    suspend fun getAllProducts(): List<Products>

    @Query("SELECT * FROM products WHERE uuid = :productId")
    suspend fun getProduct(productId: Int): Products

    @Query("DELETE FROM products ")
    suspend fun deleteAllProducts()
}