package com.omarismayilov.movaapp.ui.home

import com.omarismayilov.movaapp.data.model.MovieResponseDTO

sealed class MovieUiState {
    object Loading : MovieUiState()
    data class Success(val data: List<MovieResponseDTO>) : MovieUiState()
    data class Error(val message: String) : MovieUiState()
}