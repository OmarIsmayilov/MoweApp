package com.omarismayilov.movaapp.data.model


import com.google.gson.annotations.SerializedName

data class TrailersDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val trailerDTOS: List<TrailerDTO>
)