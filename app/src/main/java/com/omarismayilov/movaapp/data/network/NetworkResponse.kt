package com.omarismayilov.movaapp.data.network


sealed class NetworkResponse<out T> {
    data class Success<out T : Any>(val data: T) : NetworkResponse<T>()
    data class Error(val exception: String) : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()
}

