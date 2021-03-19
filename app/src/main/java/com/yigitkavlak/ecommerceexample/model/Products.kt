package com.yigitkavlak.ecommerceexample.model

import com.google.gson.annotations.SerializedName

data class Products (
    @SerializedName("currency")
    val currency: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("image")
    val image: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("price")
    val price: String
        )