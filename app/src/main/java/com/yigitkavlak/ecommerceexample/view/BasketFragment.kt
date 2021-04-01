package com.yigitkavlak.ecommerceexample.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.yigitkavlak.ecommerceexample.R
import com.yigitkavlak.ecommerceexample.databinding.FragmentBasketBinding
import com.yigitkavlak.ecommerceexample.model.Cart
import com.yigitkavlak.ecommerceexample.viewmodel.BasketViewModel
import kotlinx.android.synthetic.main.item_order.*


class BasketFragment : Fragment() {
    private lateinit var viewModel: BasketViewModel
    private lateinit var dataBinding: FragmentBasketBinding

    private var productUuid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_basket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[BasketViewModel::class.java]
        viewModel.getDataFromRoom(productUuid)
        arguments?.let {
            productUuid = BasketFragmentArgs.fromBundle(it).productUuid

        }
    }

//    fun observeLiveData() {
//        viewModel.cartList.observe(viewLifecycleOwner, Observer { product ->
//            product?.let {
//                dataBinding.
//            }
//        })
//    }


}