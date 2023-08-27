package com.omarismayilov.movaapp.ui.myList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omarismayilov.movaapp.common.utils.Resource
import com.omarismayilov.movaapp.data.model.local.MovieLocalModel
import com.omarismayilov.movaapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    val movieRepository: MovieRepository,
) : ViewModel() {

    private val _listData = MutableLiveData<MyListUiState>()
    val listData: LiveData<MyListUiState> get() = _listData


    init {
        getListData()
    }


    fun getListData() {
        viewModelScope.launch {
            movieRepository.getListData().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _listData.value = MyListUiState.SuccessListData(it.data)
                    }

                    is Resource.Error -> {
                        _listData.value = MyListUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _listData.value = MyListUiState.Loading
                    }
                }
            }
        }
    }

    fun addList(movie: MovieLocalModel) {
        viewModelScope.launch {
            movieRepository.addList(movie)
        }
    }

    fun deleteItem(id: Int) {
        viewModelScope.launch {
            movieRepository.deleteItem(id)
            this@MyListViewModel.getListData()
        }
    }
}