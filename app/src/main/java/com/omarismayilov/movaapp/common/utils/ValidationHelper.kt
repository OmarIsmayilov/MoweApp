package com.omarismayilov.movaapp.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Patterns
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.textfield.TextInputLayout
import com.omarismayilov.movaapp.R

class ValidationHelper(val context: Context) {

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    private fun isEmailValid(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }

    fun validateData(emailEditText: EditText, passwordEditText: EditText, emailLy: TextInputLayout, passLy: TextInputLayout): Boolean {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()


        if (!isEmailValid(email)) {
            emailLy.apply {
                isErrorEnabled = true
                error = context.getString(R.string.invalid_email_format)
            }
            return false
        } else {
            emailLy.isErrorEnabled = false
        }

        if (!isPasswordValid(password)) {
            passLy.apply {
                isErrorEnabled = true
                error = context.getString(R.string.password_required)
            }
            return false
        } else {
            passLy.isErrorEnabled = false
        }

        return true
    }

}
