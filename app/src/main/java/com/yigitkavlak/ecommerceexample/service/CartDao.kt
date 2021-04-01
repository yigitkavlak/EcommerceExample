package com.yigitkavlak.ecommerceexample.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yigitkavlak.ecommerceexample.model.Cart

@Dao
interface CartDao {

    @Query("SELECT * FROM Cart")
    fun getAll(): List<Cart>

    @Query("SELECT * FROM cart WHERE uid = :productId")
    suspend fun getCart(productId: Int): Cart

    @Insert
    fun insertAll(vararg item: Cart)
}