package com.omarismayilov.movaapp.data.model.response

import android.os.Parcelable
import com.omarismayilov.movaapp.common.utils.MovieTypeEnum
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieType(
    var title : String,
    var type: MovieTypeEnum
) : Parcelable
