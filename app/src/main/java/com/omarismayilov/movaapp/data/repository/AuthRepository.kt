package com.omarismayilov.movaapp.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.omarismayilov.movaapp.common.utils.Resource
import com.omarismayilov.movaapp.common.utils.SharedPrefManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val sp: SharedPrefManager,
) {
    suspend fun login(
        email: String,
        password: String,
        checked: Boolean,
    ): Flow<Resource<AuthResult>> =
        flow {
            emit(Resource.Loading)
            val user = auth.signInWithEmailAndPassword(email, password).await()
            if (checked) sp.saveToken(auth.currentUser?.uid)
            emit(Resource.Success(user))
        }.catch {
            emit(Resource.Error(it as Exception))
        }


    fun register(email: String, password: String): Flow<Resource<AuthResult>> =
        flow {
            emit(Resource.Loading)
            val user = auth.createUserWithEmailAndPassword(email, password).await()
            emit(Resource.Success(user))
        }.catch {
            emit(Resource.Error(it as Exception))
        }


    fun logOut(): Flow<Resource<Boolean>> =
        flow {
            emit(Resource.Loading)
            auth.signOut()
            emit(Resource.Success(true))
            sp.saveToken(null)
        }.catch {
            emit(Resource.Error(it as Exception))
        }


}