package com.omarismayilov.movaapp.common.utils

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.data.dto.response.AuthResponse
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

object Extensions {

    fun Activity.showMessage(response: AuthResponse) {
        MotionToast.createColorToast(
            this,
            if(response.success) "Success" else "Error" ,
            response.message ?: "Error404",
            if (response.success) MotionToastStyle.SUCCESS else MotionToastStyle.ERROR,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.SHORT_DURATION,
            null
        )
    }

    fun ImageView.loadUrl(url:String){
        Glide.with(this)
            .load("${Constants.BASE_URL_IMAGE}${url}")
            .placeholder(R.drawable.gray_placeholder)
            .into(this)
    }
}