package com.omarismayilov.movaapp.data.model


import com.google.gson.annotations.SerializedName

data class CreditDTO(
    @SerializedName("cast")
    val castDTO: List<CastDTO>,
    @SerializedName("crew")
    val crewDTO: List<CrewDTO>,
    @SerializedName("id")
    val id: Int
)