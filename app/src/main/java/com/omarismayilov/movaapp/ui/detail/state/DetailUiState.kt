package com.omarismayilov.movaapp.ui.detail.state

import com.omarismayilov.movaapp.data.model.CastDTO
import com.omarismayilov.movaapp.data.model.DetailDTO

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class SuccessDetailData(val data: DetailDTO) : DetailUiState()
    data class SuccessCastData(val data: List<CastDTO>) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}