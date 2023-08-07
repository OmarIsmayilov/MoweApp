package com.omarismayilov.movaapp.ui.auth

import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.textfield.TextInputLayout
import com.omarismayilov.movaapp.common.utils.ValidationHelper
import com.omarismayilov.movaapp.data.dto.response.AuthResponse
import com.omarismayilov.movaapp.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val helper: ValidationHelper,
) : ViewModel() {

    private val _authResult = MutableLiveData<AuthResponse>()
    val authResult: LiveData<AuthResponse> get() = _authResult

    private val _loading = MutableLiveData<Boolean>()
    val loading :LiveData<Boolean> get() = _loading


    fun loginUser(email: String, password: String, checked: Boolean) {
        viewModelScope.launch {
            _loading.postValue(true)
            repository.login(email, password, checked)?.let {
                _authResult.postValue(it)

            }
            _loading.postValue(false)
        }

    }

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            _loading.postValue(true)
            repository.register(email, password)?.let {
                _authResult.postValue(it)

            }
            _loading.postValue(false)
        }
    }

    fun logoutUser() {
        viewModelScope.launch {
            _loading.postValue(true)
            repository.logOut()?.let {
                _authResult.postValue(it)
            }
            _loading.postValue(false)
        }
    }

    fun validateData(
        etEmail: EditText, etPass: EditText,
        tlEmail: TextInputLayout, tlPass: TextInputLayout,
    ): Boolean {
        return helper.validateData(etEmail, etPass, tlEmail, tlPass)
    }

}