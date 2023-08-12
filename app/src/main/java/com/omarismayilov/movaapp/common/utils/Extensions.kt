package com.omarismayilov.movaapp.common.utils

import android.app.Activity
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.omarismayilov.movaapp.R
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

object Extensions {

    fun Activity.showMessage(
        title: String?, description: String,
        style: MotionToastStyle,
    ) {
        MotionToast.createColorToast(
            this,
            title,
            description,
            style,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.SHORT_DURATION,
            null
        )
    }

    fun ImageView.loadUrl(url: String?) {
        url?.let {
            Glide.with(this)
                .load("${Constants.BASE_URL_IMAGE}${url}")
                .placeholder(R.drawable.gray_placeholder)
                .into(this)
        }

    }


    fun LottieAnimationView.visible(){
        this.visibility = View.VISIBLE
    }

    fun LottieAnimationView.gone(){
        this.visibility = View.GONE
    }

    fun ConstraintLayout.visible(){
        this.visibility = View.VISIBLE
    }

    fun ConstraintLayout.gone(){
        this.visibility = View.GONE
    }


}