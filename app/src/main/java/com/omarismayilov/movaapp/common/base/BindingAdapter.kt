package com.omarismayilov.movaapp.common.base

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.common.utils.Constants

@BindingAdapter("load_url")
fun loadUrl(view: ShapeableImageView,url:String?) {
    url?.let {
        Glide.with(view)
            .load("${Constants.BASE_URL_IMAGE}${url}")
            .placeholder(R.drawable.gray_placeholder)
            .into(view)
    }
}