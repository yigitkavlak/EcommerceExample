package com.yigitkavlak.ecommerceexample.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.yigitkavlak.ecommerceexample.model.Products
import com.yigitkavlak.ecommerceexample.service.ApplicationDatabase
import kotlinx.coroutines.launch

class BasketViewModel(application: Application) : BaseViewModel(application) {


    val cartList = MutableLiveData<Products>()

    fun getDataFromRoom(uuid : Int){
        launch {
         val dao = ApplicationDatabase(getApplication()).productDao()
            val cart = dao.getProduct(uuid)
            cartList.value = cart

        }
    }



}