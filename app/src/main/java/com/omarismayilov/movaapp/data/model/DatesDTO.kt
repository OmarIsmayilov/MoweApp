package com.omarismayilov.movaapp.data.model


import com.google.gson.annotations.SerializedName

data class DatesDTO(
    @SerializedName("maximum")
    val maximum: String,
    @SerializedName("minimum")
    val minimum: String
)