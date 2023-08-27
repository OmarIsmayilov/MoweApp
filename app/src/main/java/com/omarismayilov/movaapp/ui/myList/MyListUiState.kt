package com.omarismayilov.movaapp.ui.myList

import com.omarismayilov.movaapp.data.model.local.MovieLocalModel

sealed class MyListUiState {
    object Loading : MyListUiState()
    data class SuccessListData(val data : List<MovieLocalModel>) : MyListUiState()
    data class Error(val message : String) : MyListUiState()
}