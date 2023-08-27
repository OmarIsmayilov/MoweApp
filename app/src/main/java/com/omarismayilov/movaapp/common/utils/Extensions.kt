package com.omarismayilov.movaapp.common.utils

import android.app.Activity
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.omarismayilov.movaapp.data.model.DetailDTO
import com.omarismayilov.movaapp.data.model.MovieResponseDTO
import com.omarismayilov.movaapp.data.model.local.MovieLocalModel
import com.shashank.sony.fancytoastlib.FancyToast
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

object Extensions {

    fun Activity.showMessage(
        description: String,
        style: Int,
    ) {
        FancyToast.makeText(this,description,FancyToast.LENGTH_SHORT,style,false).show()
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

    fun RecyclerView.visible(){
        this.visibility = View.VISIBLE
    }

    fun RecyclerView.gone(){
        this.visibility = View.GONE
    }

    fun MovieResponseDTO.toLocalModel(): MovieLocalModel {
        return MovieLocalModel(
            this.id,
            this.title,
            this.posterPath,
            this.voteAverage.toFloat()
        )
    }

    fun DetailDTO.toLocalModel(): MovieLocalModel {
        return MovieLocalModel(
            this.id,
            this.title,
            this.posterPath,
            this.voteAverage.toFloat()
        )
    }


}