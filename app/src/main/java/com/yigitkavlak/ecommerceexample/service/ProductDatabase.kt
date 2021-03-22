package com.yigitkavlak.ecommerceexample.service

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yigitkavlak.ecommerceexample.model.Products


@Database(entities = arrayOf(Products::class), version = 1)
abstract class ProductDatabase : RoomDatabase(){
    abstract fun productDa() : ProductDao
}