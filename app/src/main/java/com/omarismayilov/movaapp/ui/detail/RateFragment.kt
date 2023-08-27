package com.omarismayilov.movaapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.common.utils.Extensions.showMessage
import com.omarismayilov.movaapp.databinding.FragmentLogoutBinding
import com.omarismayilov.movaapp.databinding.FragmentRateBinding
import com.omarismayilov.movaapp.ui.auth.AuthUiState
import com.omarismayilov.movaapp.ui.auth.AuthViewModel
import com.omarismayilov.movaapp.ui.profile.LogoutFragmentDirections
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class RateFragment : BottomSheetDialogFragment(R.layout.fragment_rate) {

    private var _binding: FragmentRateBinding? = null
    private val binding get() = _binding!!
    private val args: RateFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
        setListeners()
        setData()
    }

    private fun setData() {
        with(binding) {
            tvRate.text = requireContext().getString(R.string.format_vote).format(args.rating)
            rbVote.rating = args.rating
            tvCount.text = "( ${args.rateCount} users )"
        }
    }

    private fun setListeners() {
        with(binding) {
            btnSubmit.setOnClickListener {
                val rating = ratingBar4.rating
                //viewModel.rateMovie(args.id,rating)
                dismiss()
            }
            btnCancel.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun observeEvents() {
        with(viewModel) {
            rateResponse.observe(viewLifecycleOwner) {
                if (it.success){
                    requireActivity().showMessage(
                        it.statusMessage,
                        FancyToast.SUCCESS

                    )
                }else{
                    requireActivity().showMessage(
                        it.statusMessage,
                        FancyToast.ERROR
                    )
                }
            }
        }
    }

}