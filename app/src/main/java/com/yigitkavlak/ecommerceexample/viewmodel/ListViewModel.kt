package com.yigitkavlak.ecommerceexample.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.yigitkavlak.ecommerceexample.model.Products
import com.yigitkavlak.ecommerceexample.service.ServiceClient
import com.yigitkavlak.ecommerceexample.view.ListFragmentDirections
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_product.view.*

class ListViewModel(application: Application) : BaseViewModel(application) {

    private val productService = ServiceClient()
    private val disposable = CompositeDisposable()

    val products = MutableLiveData<List<Products>>()
    val productError = MutableLiveData<Boolean>()
    val productsLoading = MutableLiveData<Boolean>()

    var tryCounter = 0

    fun getDataFromAPI() {
        productsLoading.value = true

        disposable.add(
            productService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Products>>() {
                    override fun onSuccess(t: List<Products>) {
                        tryCounter = 0
                        products.value = t
                        productError.value = false
                        productsLoading.value = false
                        Toast.makeText(getApplication(), "products from API ", Toast.LENGTH_LONG)
                            .show()
                        Log.i("ProductList", "${products.value}")
                    }

                    override fun onError(e: Throwable) {


                       if(tryCounter < 3 ){
                           getDataFromAPI()
                       }else {
                           productsLoading.value = false
                           productError.value = true
                           e.printStackTrace()
                       }

                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}