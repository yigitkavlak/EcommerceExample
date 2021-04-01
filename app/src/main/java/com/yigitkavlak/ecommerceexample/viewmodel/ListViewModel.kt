package com.yigitkavlak.ecommerceexample.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.yigitkavlak.ecommerceexample.model.Products
import com.yigitkavlak.ecommerceexample.service.ApplicationDatabase
import com.yigitkavlak.ecommerceexample.service.ServiceClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

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
                       storeInSQLite(t)
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

//    private fun getDataFromSQLite() {
//        productsLoading.value = true
//        launch {
//            val products = ProductDatabase(getApplication()).productDa().getAllProducts()
//            showProducts(products)
//            Toast.makeText(getApplication(),"Countries From SQLite",Toast.LENGTH_LONG).show()
//        }
//    }

    private fun storeInSQLite(list: List<Products>) {
        launch {
            val dao = ApplicationDatabase(getApplication()).productDao()
            dao.deleteAllProducts()
            val listLong = dao.insertAll(*list.toTypedArray()) // -> list -> individual
            var i = 0
            while (i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i += 1
            }

            showProducts(list)
        }


    }

    private fun showProducts(productList: List<Products>) {
        products.value = productList
        productsLoading.value = false
        productError.value = false
    }





}