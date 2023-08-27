package com.omarismayilov.movaapp.ui.profile

import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omarismayilov.movaapp.common.utils.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sp: SharedPrefManager,
) : ViewModel() {



    fun savePhoto(uri: String?) {
        uri?.let {
            sp.savePhoto(uri)
        }
    }

    fun getPhoto() = sp.getPhoto()?.toUri()


    fun saveTheme(theme: Boolean) {
        sp.saveTheme(theme)
    }

    fun getTheme() = sp.getTheme()


}
