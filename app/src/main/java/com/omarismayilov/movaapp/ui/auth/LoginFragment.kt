package com.omarismayilov.movaapp.ui.auth

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.common.utils.Extensions.gone
import com.omarismayilov.movaapp.common.utils.Extensions.showMessage
import com.omarismayilov.movaapp.common.utils.Extensions.visible
import com.omarismayilov.movaapp.common.utils.ValidationHelper
import com.omarismayilov.movaapp.databinding.FragmentLoginBinding
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: AuthViewModel by viewModels()
    override fun observeEvents() {
        with(viewModel) {
            authResult.observe(viewLifecycleOwner) {
                with(binding){
                    when (it) {
                        is AuthUiState.Success -> {
                            loginLoading.gone()
                            requireActivity().showMessage(
                                it.message,
                                FancyToast.SUCCESS
                            )
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                        }

                        is AuthUiState.Error -> {
                            loginLoading.gone()
                            requireActivity().showMessage(
                                it.message,
                                FancyToast.ERROR
                            )
                        }

                        is AuthUiState.Loading -> {
                            loginLoading.visible()
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
            ibBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnLogin.setOnClickListener {
                validateData()
            }
            tvSignUp.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }

    }

    private fun validateData() {
        with(binding) {
            val validate = viewModel.validateData(etEmail, etPass, tlEmail, tlPass)
            if (validate) {
                viewModel.loginUser(
                    etEmail.text.toString(),
                    etPass.text.toString(),
                    cbRemember.isChecked
                )
            }
        }
    }

}