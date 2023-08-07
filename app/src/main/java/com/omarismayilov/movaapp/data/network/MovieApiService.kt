package com.omarismayilov.movaapp.data.network

import com.omarismayilov.movaapp.data.dto.NowPlayingDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingApi(@Query("api_key") apiKey : String): Response<NowPlayingDTO>

}