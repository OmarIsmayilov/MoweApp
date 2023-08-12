package com.omarismayilov.movaapp.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omarismayilov.movaapp.common.utils.Resource
import com.omarismayilov.movaapp.data.repository.MovieRepository
import com.omarismayilov.movaapp.ui.home.MovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExploreViewModel @Inject constructor(
    val repository: MovieRepository,
) : ViewModel() {

    private val _searchData = MutableLiveData<MovieUiState>()
    val searchData: LiveData<MovieUiState> get() = _searchData


    private val _exploreData = MutableLiveData<MovieUiState>()
    val exploreData: LiveData<MovieUiState> get() = _exploreData

    init {
        getTopRated()
    }

    private fun getTopRated() {
        viewModelScope.launch {
            repository.getTopRated().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _exploreData.value = MovieUiState.Success(it.data.results)
                    }

                    is Resource.Error -> {
                        _exploreData.value = MovieUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _exploreData.value = MovieUiState.Loading
                    }
                }
            }
        }
    }
    fun getSearch(query: String) {
        viewModelScope.launch {
            repository.getSearchData(query).collectLatest {
                when (it) {
                    is Resource.Success->{_searchData.value = MovieUiState.Success(it.data.results)}
                    is Resource.Error->{_searchData.value = MovieUiState.Error(it.exception.message.toString())}
                    is Resource.Loading->{_searchData.value = MovieUiState.Loading}
                }
            }
        }

    }
}