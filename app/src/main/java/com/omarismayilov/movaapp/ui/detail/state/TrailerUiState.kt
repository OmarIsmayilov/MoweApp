package com.omarismayilov.movaapp.ui.detail.state

import com.omarismayilov.movaapp.data.model.TrailerDTO

sealed class TrailerUiState {
    object Loading : TrailerUiState()
    data class SuccessTrailerData(val data: List<TrailerDTO>) : TrailerUiState()
    data class Error(val message: String) : TrailerUiState()
}
