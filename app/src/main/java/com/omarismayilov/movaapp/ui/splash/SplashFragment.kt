package com.omarismayilov.movaapp.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.common.utils.ValidationHelper
import com.omarismayilov.movaapp.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val viewModel : SplashViewModel by viewModels()
    override fun observeEvents() {
      viewModel.auth.observe(viewLifecycleOwner){
          if (ValidationHelper(requireContext()).isInternetAvailable(requireContext())){
              if (it){
                  findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
              }else{
                  findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToWelcomeFragment())
              }
          }
        }
    }

    override fun onCreateFinish() {}

    override fun setupListeners() {}


}