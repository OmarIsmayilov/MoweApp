package com.omarismayilov.movaapp.ui.auth

import androidx.navigation.fragment.findNavController
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.databinding.FragmentAuthBinding

class AuthFragment : BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {


    override fun observeEvents() {

    }

    override fun onCreateFinish() {


    }

    override fun setupListeners() {
        with(binding){
            btnSignPass.setOnClickListener {
                findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToLoginFragment())
            }
            textViewSignUp.setOnClickListener{
                findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToRegisterFragment())
            }
            imageButton6.setOnClickListener {
                findNavController().popBackStack()
            }
            btnGoogle.setOnClickListener {

            }
        }
    }




}

