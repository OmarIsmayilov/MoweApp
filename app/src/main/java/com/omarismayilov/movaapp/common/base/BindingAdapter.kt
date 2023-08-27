package com.omarismayilov.movaapp.common.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.common.utils.Constants
import com.omarismayilov.movaapp.common.utils.Constants.BASE_URL_IMAGE_YOUTUBE
import com.omarismayilov.movaapp.common.utils.Constants.YOUTUBE_QUALITY

object BindingAdapter {
    @BindingAdapter("load_url")
    @JvmStatic
    fun loadUrl(view: ShapeableImageView, url: String?) {
        url?.let {
            Glide.with(view)
                .load("${Constants.BASE_URL_IMAGE}${url}")
                .placeholder(R.color.et_background)
                .into(view)
        }
    }


    @BindingAdapter("load_youtube")
    @JvmStatic
    fun loadYoutube(view: ShapeableImageView, url: String?) {
        url?.let {
            Glide.with(view)
                .load("${BASE_URL_IMAGE_YOUTUBE}${url}${YOUTUBE_QUALITY}")
                .placeholder(R.drawable.gray_placeholder)
                .into(view)
        }
    }

}