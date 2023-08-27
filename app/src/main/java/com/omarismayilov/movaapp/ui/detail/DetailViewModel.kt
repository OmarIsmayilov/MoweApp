package com.omarismayilov.movaapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omarismayilov.movaapp.common.utils.Resource
import com.omarismayilov.movaapp.data.model.RateDTO
import com.omarismayilov.movaapp.data.repository.MovieRepository
import com.omarismayilov.movaapp.ui.detail.state.DetailUiState
import com.omarismayilov.movaapp.ui.detail.state.RecommendationUiState
import com.omarismayilov.movaapp.ui.detail.state.ReviewUiState
import com.omarismayilov.movaapp.ui.detail.state.TrailerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _detailData = MutableLiveData<DetailUiState>()
    val detailData: LiveData<DetailUiState> get() = _detailData


    private val _castData = MutableLiveData<DetailUiState>()
    val castData: LiveData<DetailUiState> get() = _castData


    private val _trailerData= MutableLiveData<TrailerUiState>()
    val trailerData: LiveData<TrailerUiState> get() = _trailerData


    private val _reviewData = MutableLiveData<ReviewUiState>()
    val reviewData: LiveData<ReviewUiState> get() = _reviewData


    private val _rateResponse = MutableLiveData<RateDTO>()
    val rateResponse: LiveData<RateDTO> get() = _rateResponse

    private val _recomendationData = MutableLiveData<RecommendationUiState>()
    val recomendationData: LiveData<RecommendationUiState> get() = _recomendationData



     fun getDetail(id: Int) {
        viewModelScope.launch {
            movieRepository.getDetail(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _detailData.value = DetailUiState.SuccessDetailData(it.data)
                    }

                    is Resource.Error -> {
                        _detailData.value = DetailUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _detailData.value = DetailUiState.Loading
                    }
                }
            }
        }
    }


    fun getCredits(id: Int) {
        viewModelScope.launch {
            movieRepository.getCredit(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _castData.value = DetailUiState.SuccessCastData(it.data.castDTO)
                    }

                    is Resource.Error -> {
                        _castData.value = DetailUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _castData.value = DetailUiState.Loading
                    }
                }
            }
        }
    }

    fun getRecommendation(id: Int) {
        viewModelScope.launch {
            movieRepository.getRecommendation(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _recomendationData.value = RecommendationUiState.SuccessData(it.data.results)
                    }

                    is Resource.Error -> {
                        _recomendationData.value = RecommendationUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _recomendationData.value = RecommendationUiState.Loading
                    }
                }
            }
        }
    }

    fun getReview(id: Int) {
        viewModelScope.launch {
            movieRepository.getReview(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _reviewData.value = ReviewUiState.SuccessData(it.data.reviewDTOS)
                    }

                    is Resource.Error -> {
                        _reviewData.value = ReviewUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _reviewData.value = ReviewUiState.Loading
                    }
                }
            }
        }
    }

    fun getTrailer(id: Int) {
        viewModelScope.launch {
            movieRepository.getTrailer(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _trailerData.value = TrailerUiState.SuccessTrailerData(it.data.trailerDTOS)
                    }

                    is Resource.Error -> {
                        _trailerData.value = TrailerUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _trailerData.value = TrailerUiState.Loading
                    }
                }
            }
        }
    }

    fun rateMovie(id: Int,rate:Float) {
        viewModelScope.launch {
            movieRepository.rateMovie(id,rate).collectLatest {
               _rateResponse.value = it
            }
        }
    }




}