package com.yigitkavlak.ecommerceexample.service

import com.yigitkavlak.ecommerceexample.model.Products
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface ProductsAPI {
    @Headers("Content-Type: application/json")
    @GET("listing")
    fun getProducts(): Single<List<Products>>
}