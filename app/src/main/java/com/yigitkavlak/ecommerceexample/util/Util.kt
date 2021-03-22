package com.yigitkavlak.ecommerceexample.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yigitkavlak.ecommerceexample.R
import kotlinx.android.synthetic.main.item_product.view.*

fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable) {

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.dis_fircasi)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

fun placeHolderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context)
        .apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }


}

@BindingAdapter("android:downloadUrl")
fun downloadUrl(view: ImageView, url: String?) {
    view.product_image.downloadFromUrl(url, placeHolderProgressBar(view.context))
}

