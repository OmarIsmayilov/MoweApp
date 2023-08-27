package com.omarismayilov.movaapp.ui.auth

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseUser
import com.omarismayilov.movaapp.common.utils.Resource
import com.omarismayilov.movaapp.common.utils.ValidationHelper
import com.omarismayilov.movaapp.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val helper: ValidationHelper,
) : ViewModel() {

    private val _authResult = MutableLiveData<AuthUiState>()
    val authResult: LiveData<AuthUiState> get() = _authResult


    fun loginUser(email: String, password: String, checked: Boolean) {
        viewModelScope.launch {
            repository.login(email, password, checked).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _authResult.value = AuthUiState.Success("Succesfully log in")
                        Log.e(TAG, "loginUser: success", )
                    }

                    is Resource.Error -> {
                        _authResult.value = AuthUiState.Error(it.exception.message.toString())
                        Log.e(TAG, "loginUser: error", )
                    }

                    is Resource.Loading -> {
                        _authResult.value = AuthUiState.Loading
                    }
                }
            }
        }

    }

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            repository.register(email, password).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _authResult.value = AuthUiState.Success("Succesfully create user")
                    }

                    is Resource.Error -> {
                        _authResult.value = AuthUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _authResult.value = AuthUiState.Loading

                    }
                }
            }
        }
    }

    fun logoutUser() {
        viewModelScope.launch {
            repository.logOut().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _authResult.value = AuthUiState.Success("Succesfully log out")
                    }

                    is Resource.Error -> {
                        _authResult.value = AuthUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _authResult.value = AuthUiState.Loading
                    }
                }
            }
        }
    }

    fun getUserInfo(): FirebaseUser? = repository.getUserData()


    fun validateData(
        etEmail: EditText, etPass: EditText,
        tlEmail: TextInputLayout, tlPass: TextInputLayout,
    ): Boolean {
        return helper.validateData(etEmail, etPass, tlEmail, tlPass)
    }

}