package com.omarismayilov.movaapp.data.repository

import com.omarismayilov.movaapp.common.utils.Constants.API_KEY
import com.omarismayilov.movaapp.data.dto.NowPlayingDTO
import com.omarismayilov.movaapp.data.network.MovieApiService
import com.omarismayilov.movaapp.data.network.NetworkResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieApiService,
) {

    suspend fun getNowPlaying(): NetworkResponse<NowPlayingDTO> {
        return try {
            val response = movieService.getNowPlayingApi(API_KEY)
            if (response.isSuccessful) {
                val nowPlayingDTO = response.body()
                if (nowPlayingDTO != null) {
                    NetworkResponse.Success(nowPlayingDTO)
                } else {
                    NetworkResponse.Error("Now playing data is null")
                }
            } else {
                NetworkResponse.Error("Failed to fetch now playing movies")
            }
        } catch (e: Exception) {
            NetworkResponse.Error(e.localizedMessage.toString())
        }
    }


}