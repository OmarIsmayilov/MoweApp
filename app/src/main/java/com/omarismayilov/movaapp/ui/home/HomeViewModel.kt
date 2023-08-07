package com.omarismayilov.movaapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omarismayilov.movaapp.data.dto.NowPlayingDTO
import com.omarismayilov.movaapp.data.network.NetworkResponse
import com.omarismayilov.movaapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _nowPlayingMovies = MutableLiveData<NetworkResponse<NowPlayingDTO>>()
    val nowPlayingMovies: LiveData<NetworkResponse<NowPlayingDTO>> = _nowPlayingMovies

    init {
        getNowPlaying()
    }

     private fun getNowPlaying() {
         viewModelScope.launch {
             _nowPlayingMovies.value = NetworkResponse.Loading
             val response = movieRepository.getNowPlaying()
             _nowPlayingMovies.value = response
         }
     }

    }


