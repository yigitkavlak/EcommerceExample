package com.yigitkavlak.ecommerceexample.service

import com.yigitkavlak.ecommerceexample.model.Products
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceClient {
    private val BASE_URL = "https://nonchalant-fang.glitch.me/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ProductsAPI::class.java)

    fun getData(): Single<List<Products>> {
        return api.getProducts()
    }
}