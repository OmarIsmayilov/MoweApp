package com.omarismayilov.movaapp.data.network

import com.google.gson.JsonObject
import com.omarismayilov.movaapp.common.utils.Constants.API_KEY
import com.omarismayilov.movaapp.data.model.CreditDTO
import com.omarismayilov.movaapp.data.model.DetailDTO
import com.omarismayilov.movaapp.data.model.GenreDTO
import com.omarismayilov.movaapp.data.model.NowPlayingDTO
import com.omarismayilov.movaapp.data.model.RateDTO
import com.omarismayilov.movaapp.data.model.ReviewsDTO
import com.omarismayilov.movaapp.data.model.TopRatedDTO
import com.omarismayilov.movaapp.data.model.TrailersDTO
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingApi(@Query("api_key") apiKey: String = API_KEY): NowPlayingDTO

    @GET("trending/movie/{time_window}")
    suspend fun getTrending(
        @Path("time_window") window: String = "week",
        @Query("api_key") apiKey: String = API_KEY,
    ): TopRatedDTO

    @GET("movie/top_rated")
    suspend fun getTopRated(@Query("api_key") apiKey: String = API_KEY): TopRatedDTO

    @GET("movie/upcoming")
    suspend fun getUpComing(@Query("api_key") apiKey: String = API_KEY): NowPlayingDTO

    @GET("search/movie")
    suspend fun getSearch(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = API_KEY,
    ): TopRatedDTO

    @GET("discover/movie")
    suspend fun getMovieFilter(
        @Query("with_genre") genre: String,
        @Query("primary_release_year") period: Int?,
        @Query("region") region: String,
        @Query("sort_by") sort: String,
        @Query("api_key") apiKey: String = API_KEY,
    ): TopRatedDTO

    @GET("discover/tv")
    suspend fun getTvFilter(
        @Query("with_genre") genre: String,
        @Query("primary_release_year") period: Int?,
        @Query("region") region: String,
        @Query("sort_by") sort: String,
        @Query("api_key") apiKey: String = API_KEY,
    ): TopRatedDTO

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): DetailDTO

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): CreditDTO

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendation(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): TopRatedDTO

    @GET("movie/{movie_id}/reviews")
    suspend fun getReview(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): ReviewsDTO

    @GET("movie/{movie_id}/videos")
    suspend fun getTrailer(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): TrailersDTO

    @POST("movie/{movie_id}/rating")
    suspend fun rateMovie(
        @Path("movie_id") id: Int,
        @Body body: String,
        @Query("api_key") apiKey: String = API_KEY
    ): RateDTO


}