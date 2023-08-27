package com.omarismayilov.movaapp.ui.detail

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
import com.omarismayilov.movaapp.databinding.FragmentTrailerBinding
import com.omarismayilov.movaapp.ui.detail.adapter.TrailerAdapter
import com.omarismayilov.movaapp.ui.detail.state.ReviewUiState
import com.omarismayilov.movaapp.ui.detail.state.TrailerUiState
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class TrailerFragment(private val mId: Int) :
    BaseFragment<FragmentTrailerBinding>(FragmentTrailerBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()
    private val trailerAdapter = TrailerAdapter()

    override fun observeEvents() {
        with(viewModel) {
            with(binding) {
                trailerData.observe(viewLifecycleOwner) {
                    when (it) {
                        is TrailerUiState.SuccessTrailerData -> {
                            loginLoading.gone()
                            trailerAdapter.differ.submitList(it.data)
                        }

                        is TrailerUiState.Loading -> {
                            loginLoading.visible()
                        }

                        is TrailerUiState.Error -> {
                            loginLoading.gone()
                            requireActivity().showMessage(
                                it.message,
                                FancyToast.ERROR
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onCreateFinish() {
        setAdapter()
        viewModel.getTrailer(mId)
    }

    private fun setAdapter() {
        binding.rvTrailer.adapter = trailerAdapter
    }

    override fun setupListeners() {
        trailerAdapter.onClick = {
            findNavController().navigate(
                DetailFragmentDirections.actionDetailFragmentToYoutubePlayerFragment(
                    it
                )
            )
        }
    }

}