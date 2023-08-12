package com.omarismayilov.movaapp.data.network

import com.omarismayilov.movaapp.common.utils.Constants.API_KEY
import com.omarismayilov.movaapp.data.model.NowPlayingDTO
import com.omarismayilov.movaapp.data.model.TopRatedDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingApi(@Query("api_key") apiKey: String = API_KEY, ): NowPlayingDTO
    @GET("trending/movie/{time_window}")
    suspend fun getTrending(@Path("time_window") window: String = "week", @Query("api_key") apiKey: String = API_KEY, ): TopRatedDTO

    @GET("movie/top_rated")
    suspend fun getTopRated(@Query("api_key") apiKey: String = API_KEY ): TopRatedDTO

    @GET("movie/upcoming")
    suspend fun getUpComing(@Query("api_key") apiKey: String = API_KEY ): NowPlayingDTO

    @GET("search/movie")
    suspend fun getSearch(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = API_KEY
    ) : TopRatedDTO

}