package com.omarismayilov.movaapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.common.utils.Extensions.gone
import com.omarismayilov.movaapp.common.utils.Extensions.showMessage
import com.omarismayilov.movaapp.common.utils.Extensions.visible
import com.omarismayilov.movaapp.databinding.FragmentCommentBinding
import com.omarismayilov.movaapp.ui.detail.adapter.ReviewAdapter
import com.omarismayilov.movaapp.ui.detail.state.RecommendationUiState
import com.omarismayilov.movaapp.ui.detail.state.ReviewUiState
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class CommentFragment(private val mId: Int) :
    BaseFragment<FragmentCommentBinding>(FragmentCommentBinding::inflate) {
    private val viewModel: DetailViewModel by viewModels()
    private val reviewAdapter = ReviewAdapter()

    override fun observeEvents() {
        with(viewModel) {
            with(binding) {
                reviewData.observe(viewLifecycleOwner) {
                    when (it) {
                        is ReviewUiState.SuccessData -> {
                            loginLoading.gone()
                            reviewAdapter.differ.submitList(it.data)
                            tvSize.text = "${it.data.size} Comments"
                        }

                        is ReviewUiState.Loading -> {
                            loginLoading.visible()
                        }

                        is ReviewUiState.Error -> {
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
        viewModel.getReview(mId)
    }

    private fun setAdapter() {
        binding.rvReview.adapter = reviewAdapter
    }

    override fun setupListeners() {

    }

}