package com.omarismayilov.movaapp.ui.detail.state

import com.omarismayilov.movaapp.data.model.CastDTO
import com.omarismayilov.movaapp.data.model.DetailDTO
import com.omarismayilov.movaapp.data.model.MovieResponseDTO

sealed class RecommendationUiState {
    object Loading : RecommendationUiState()
    data class SuccessData(val data: List<MovieResponseDTO>) : RecommendationUiState()
    data class Error(val message: String) : RecommendationUiState()
}
