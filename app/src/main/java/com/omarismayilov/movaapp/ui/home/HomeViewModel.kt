package com.omarismayilov.movaapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omarismayilov.movaapp.common.utils.Resource
import com.omarismayilov.movaapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _nowPlayingMovies = MutableLiveData<MovieUiState>()
    val nowPlayingMovies: LiveData<MovieUiState> = _nowPlayingMovies

    private val _trendingMovies = MutableLiveData<MovieUiState>()
    val topRatedMovies: LiveData<MovieUiState> get() = _trendingMovies

    private val _upComingMovies = MutableLiveData<MovieUiState>()
    val upComingMovies: LiveData<MovieUiState> = _upComingMovies

    init {
        getNowPlaying()
        getWeeklyTrending()
        getUpcoming()
    }

    private fun getUpcoming() {
        viewModelScope.launch {
            movieRepository.getUpcoming().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _upComingMovies.value = MovieUiState.Success(it.data.movieResponseDTOS)
                    }

                    is Resource.Error -> {
                        _upComingMovies.value = MovieUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _upComingMovies.value = MovieUiState.Loading
                    }
                }
            }
        }
    }

    private fun getNowPlaying() {
        viewModelScope.launch {
            movieRepository.getNowPlaying().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _nowPlayingMovies.value = MovieUiState.Success(it.data.movieResponseDTOS)
                    }

                    is Resource.Error -> {
                        _nowPlayingMovies.value = MovieUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _nowPlayingMovies.value = MovieUiState.Loading
                    }
                }
            }
        }
    }


    private fun getWeeklyTrending() {
        viewModelScope.launch {
            movieRepository.getTrending().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _trendingMovies.value = MovieUiState.Success(it.data.results)
                    }

                    is Resource.Error -> {
                        _trendingMovies.value = MovieUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _trendingMovies.value = MovieUiState.Loading
                    }
                }
            }
        }
    }

}


