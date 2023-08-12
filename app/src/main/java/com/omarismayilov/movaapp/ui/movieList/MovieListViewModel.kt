package com.omarismayilov.movaapp.ui.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omarismayilov.movaapp.common.utils.Resource
import com.omarismayilov.movaapp.data.model.MovieResponseDTO
import com.omarismayilov.movaapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    val repository: MovieRepository,
) : ViewModel() {

    private val _movieData = MutableLiveData<List<MovieResponseDTO>>()
    val movieData: LiveData<List<MovieResponseDTO>> get() = _movieData

    fun getTrending() {
        viewModelScope.launch {
            repository.getTrending().collectLatest {
                when (it) {
                    is Resource.Success -> { _movieData.value = it.data.results }
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                }
            }
        }
    }

    fun getUpcoming() {
        viewModelScope.launch {
            repository.getUpcoming().collectLatest {
                when (it) {
                    is Resource.Success -> { _movieData.value = it.data.movieResponseDTOS }
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                }
            }
        }
    }


}