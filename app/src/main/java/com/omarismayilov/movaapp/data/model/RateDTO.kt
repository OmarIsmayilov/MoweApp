package com.omarismayilov.movaapp.data.model


import com.google.gson.annotations.SerializedName

data class RateDTO(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String,
    @SerializedName("success")
    val success: Boolean
)