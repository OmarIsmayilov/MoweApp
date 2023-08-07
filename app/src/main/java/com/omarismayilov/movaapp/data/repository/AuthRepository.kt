package com.omarismayilov.movaapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.omarismayilov.movaapp.common.utils.SharedPrefManager
import com.omarismayilov.movaapp.data.dto.response.AuthResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val sp: SharedPrefManager,
) {

    private val authModel = MutableLiveData<AuthResponse>()

    suspend fun login(email: String, password: String, checked: Boolean): AuthResponse? {
        return withContext(Dispatchers.IO) {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        if (checked) sp.saveToken(it.user?.uid)
                        authModel.postValue(AuthResponse("Succesfully login", true))
                    }
                    .addOnFailureListener {
                        authModel.postValue(it.localizedMessage?.let { it1 ->
                            AuthResponse(
                                it1,
                                false
                            )
                        })
                    }
                authModel.value
            } catch (e: Exception) {
                e.localizedMessage?.let { AuthResponse(it, false) }
            }
        }
    }


    suspend fun register(email: String, password: String): AuthResponse? {
        return withContext(Dispatchers.IO) {
            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        authModel.postValue(AuthResponse("Create user succesfully", true))
                    }
                    .addOnFailureListener {
                        authModel.postValue(AuthResponse(it.localizedMessage, false))
                    }
                authModel.value
            } catch (e: Exception) {
                AuthResponse(e.localizedMessage, false)
            }
        }

    }


    suspend fun logOut(): AuthResponse? {
        return withContext(Dispatchers.IO) {
            try {
                auth.signOut()
                authModel.postValue(AuthResponse("Succesfully sign out", true))
                sp.saveToken(null)
                authModel.value
            } catch (e: Exception) {
                AuthResponse(e.localizedMessage, false)
            }
        }

    }


}