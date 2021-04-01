package com.yigitkavlak.ecommerceexample.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yigitkavlak.ecommerceexample.model.Cart
import com.yigitkavlak.ecommerceexample.model.Products


@Database(entities = arrayOf(Products::class), version = 1)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
   // abstract fun cartDao(): CartDao

    //Singleton

    companion object {
        @Volatile
        private var instance: ApplicationDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, ApplicationDatabase::class.java, "application_database"
        ).build()
    }
}




