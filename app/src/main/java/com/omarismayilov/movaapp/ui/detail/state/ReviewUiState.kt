package com.omarismayilov.movaapp.ui.detail.state

import com.omarismayilov.movaapp.data.model.CastDTO
import com.omarismayilov.movaapp.data.model.DetailDTO
import com.omarismayilov.movaapp.data.model.ReviewDTO

sealed class ReviewUiState {
    object Loading : ReviewUiState()
    data class SuccessData(val data: List<ReviewDTO>) : ReviewUiState()
    data class Error(val message: String) : ReviewUiState()
}