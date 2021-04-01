package com.yigitkavlak.ecommerceexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.yigitkavlak.ecommerceexample.R
import com.yigitkavlak.ecommerceexample.databinding.ItemProductBinding
import com.yigitkavlak.ecommerceexample.model.Products
import com.yigitkavlak.ecommerceexample.view.ListFragmentDirections


class ProductRecyclerAdapter(private val productList: ArrayList<Products>) :
    RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>(), ProductButtonClickListener {
    class ProductViewHolder(var view: ItemProductBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemProductBinding>(
            inflater,
            R.layout.item_product,
            parent,
            false
        )
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.view.listener = this
        holder.view.products = productList[position]


    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateProductList(newProductList: List<Products>) {
        productList.clear()
        productList.addAll(newProductList)
        notifyDataSetChanged()
    }

    override fun onClicked(v: View) {
        val action = ListFragmentDirections.actionListFragmentToBasketFragment()
        v.findNavController().navigate(action)
    }


}