package com.omarismayilov.movaapp.ui.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.common.utils.Extensions.gone
import com.omarismayilov.movaapp.common.utils.Extensions.showMessage
import com.omarismayilov.movaapp.common.utils.Extensions.visible
import com.omarismayilov.movaapp.databinding.FragmentRecommedationBinding
import com.omarismayilov.movaapp.ui.detail.adapter.RecommendationAdapter
import com.omarismayilov.movaapp.ui.detail.state.RecommendationUiState
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle


@AndroidEntryPoint
class RecommendationFragment(val mId: Int) :
    BaseFragment<FragmentRecommedationBinding>(FragmentRecommedationBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()
    private val recommendationAdapter = RecommendationAdapter()

    override fun observeEvents() {
        with(viewModel) {
            with(binding) {
                recomendationData.observe(viewLifecycleOwner) {
                    when (it) {
                        is RecommendationUiState.SuccessData -> {
                            loginLoading.gone()
                            recommendationAdapter.differ.submitList(it.data)
                        }

                        is RecommendationUiState.Loading -> {
                            loginLoading.visible()
                        }

                        is RecommendationUiState.Error -> {
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
        with(binding) {
            setAdapter()
            viewModel.getRecommendation(mId)
        }
    }

    private fun setAdapter() {
        binding.rvRecommendation.adapter = recommendationAdapter
    }

    override fun setupListeners() {}

}