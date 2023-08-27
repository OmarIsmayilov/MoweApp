package com.omarismayilov.movaapp.data.model


import com.google.gson.annotations.SerializedName

data class ReviewsDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val reviewDTOS: List<ReviewDTO>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)