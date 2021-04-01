package com.yigitkavlak.ecommerceexample.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Cart (


    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,


    @ColumnInfo(name = "price")
    @SerializedName("price")
     val price: String,

    @ColumnInfo(name = "quantity")
    @SerializedName("quantity")
   val quantity: Int,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    val image: String

){
    @PrimaryKey(autoGenerate = true)
    val uid: Int? = 0
}