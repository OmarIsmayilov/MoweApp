package com.omarismayilov.movaapp.data.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilterOption(
    val category: String,
    val genre: List<String>,
    val region: String,
    val period: String,
    val sort: String,
) : Parcelable
