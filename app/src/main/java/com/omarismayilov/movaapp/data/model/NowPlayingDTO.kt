package com.omarismayilov.movaapp.data.model


import com.google.gson.annotations.SerializedName

data class NowPlayingDTO(
    @SerializedName("dates")
    val datesDTO: DatesDTO,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movieResponseDTOS: List<MovieResponseDTO>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)