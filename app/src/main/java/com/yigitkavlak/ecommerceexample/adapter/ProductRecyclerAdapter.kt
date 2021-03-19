package com.yigitkavlak.ecommerceexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yigitkavlak.ecommerceexample.R
import com.yigitkavlak.ecommerceexample.model.Products
import com.yigitkavlak.ecommerceexample.util.downloadFromAPI
import com.yigitkavlak.ecommerceexample.util.placeHolderProgressBar
import kotlinx.android.synthetic.main.item_product.view.*

class ProductRecyclerAdapter(val productList: ArrayList<Products>) :
    RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>() {
    class ProductViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.view.product_name.text = productList[position].name
        holder.view.product_image.downloadFromAPI(
            productList[position].image,
            placeHolderProgressBar(holder.view.context)
        )
        holder.view.product_price.text = productList[position].price
        holder.view.product_currency.text = productList[position].currency

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateProductList(newProductList: List<Products>) {
        productList.clear()
        productList.addAll(newProductList)
        notifyDataSetChanged()
    }


}