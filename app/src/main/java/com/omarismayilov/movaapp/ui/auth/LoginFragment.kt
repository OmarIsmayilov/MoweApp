package com.omarismayilov.movaapp.ui.auth

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.common.utils.Extensions.showMessage
import com.omarismayilov.movaapp.common.utils.ValidationHelper
import com.omarismayilov.movaapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: AuthViewModel by viewModels()
    override fun observeEvents() {
        with(viewModel) {
            authResult.observe(viewLifecycleOwner) {
                requireActivity().showMessage(it)
                if (it.success) {
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                }else {
                    binding.loginLoading.visibility = View.INVISIBLE
                }
            }
            loading.observe(viewLifecycleOwner) {
                binding.loginLoading.visibility = if (it) View.VISIBLE else View.INVISIBLE
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