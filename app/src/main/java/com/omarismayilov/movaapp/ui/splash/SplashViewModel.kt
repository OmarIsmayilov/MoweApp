package com.omarismayilov.movaapp.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omarismayilov.movaapp.common.utils.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sp: SharedPrefManager,
) : ViewModel() {

    private val _auth = MutableLiveData<Boolean>()
    val auth: LiveData<Boolean> get() = _auth

    init{
        checkAuth()
    }

    fun checkAuth() {
        viewModelScope.launch {
            delay(2500)
            _auth.value = sp.getToken()?.let {
                it!=""
            }
        }
    }

}