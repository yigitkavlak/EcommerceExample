package com.yigitkavlak.ecommerceexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yigitkavlak.ecommerceexample.R
import com.yigitkavlak.ecommerceexample.model.Cart

class CardRecyclerAdapter(val cartList: ArrayList<Cart>) :
    RecyclerView.Adapter<CardRecyclerAdapter.CartViewHolder>() {
    class CartViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)


        val view = inflater.inflate(R.layout.item_order, parent, false)


        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return cartList.size
    }
}