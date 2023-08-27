package com.omarismayilov.movaapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.common.utils.Extensions.gone
import com.omarismayilov.movaapp.common.utils.Extensions.showMessage
import com.omarismayilov.movaapp.common.utils.Extensions.visible
import com.omarismayilov.movaapp.common.utils.ValidationHelper
import com.omarismayilov.movaapp.databinding.FragmentRegisterBinding
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: AuthViewModel by viewModels()
    override fun observeEvents() {
        with(viewModel) {

            authResult.observe(viewLifecycleOwner) {
                with(binding){
                    when (it) {
                        is AuthUiState.Success -> {
                            loginLoading2.gone()
                            requireActivity().showMessage(
                                it.message,
                                FancyToast.SUCCESS
                            )
                            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                        }

                        is AuthUiState.Error -> {
                            loginLoading2.gone()
                            requireActivity().showMessage(
                                it.message,
                                FancyToast.ERROR
                            )
                        }

                        is AuthUiState.Loading -> {
                            loginLoading2.visible()
                        }
                    }
                }
            }
        }
    }

    override fun onCreateFinish() {

    }

    override fun setupListeners() {
        with(binding) {
            btnRegister.setOnClickListener {
                validateData()
            }
            tvSignIn.setOnClickListener {
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            }
            ibBack.setOnClickListener {
                findNavController().popBackStack()
            }

        }
    }

    private fun validateData() {
        with(binding) {
            val validate = viewModel.validateData(etEmail,etPass,tlEmail, tlPass)
            if (validate) {
                viewModel.registerUser(etEmail.text.toString(), etPass.text.toString())
            }
        }
    }


}