package com.yigitkavlak.ecommerceexample.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.yigitkavlak.ecommerceexample.R
import com.yigitkavlak.ecommerceexample.adapter.ProductRecyclerAdapter
import com.yigitkavlak.ecommerceexample.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.item_product.*
import kotlinx.android.synthetic.main.item_product.view.*


class ListFragment : Fragment() {


    private lateinit var viewModel: ListViewModel
    private val productAdapter = ProductRecyclerAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.getDataFromAPI()

        //  recyclerView.layoutManager = LinearLayoutManager(context)
        val manager = GridLayoutManager(context, 2)

        recyclerView.layoutManager = manager
        recyclerView.adapter = productAdapter


        refreshData()
        observeLiveData()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeLiveData() {
        viewModel.products.observe(viewLifecycleOwner, Observer { product ->
            product?.let {
                recyclerView.visibility = View.VISIBLE
                productAdapter.updateProductList(product)
            }
        })

        viewModel.productError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    error_text.visibility = View.VISIBLE
                } else {
                    error_text.visibility = View.GONE
                }
            }
        })

        viewModel.productsLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    recyclerView.visibility = View.GONE
                    error_text.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE

                } else {
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun refreshData() {
        swipeRefreshLayout.setOnRefreshListener {
            recyclerView.visibility = View.GONE
            error_text.visibility = View.GONE
            progressBar.visibility = View.GONE
            viewModel.getDataFromAPI()
            swipeRefreshLayout.isRefreshing = false
        }
    }


}