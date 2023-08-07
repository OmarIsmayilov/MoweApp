package com.omarismayilov.movaapp.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.common.utils.Extensions.showMessage
import com.omarismayilov.movaapp.databinding.FragmentLogoutBinding
import com.omarismayilov.movaapp.ui.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogoutFragment : BottomSheetDialogFragment(R.layout.fragment_logout) {

    private var _binding: FragmentLogoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogoutBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
        setListeners()


    }

    private fun setListeners() {
        with(binding) {
            btnLogout.setOnClickListener {
                viewModel.logoutUser()
            }
            btnCancel.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun observeEvents() {
        with(viewModel){
            authResult.observe(viewLifecycleOwner){
                requireActivity().showMessage(it)
                if(it.success){
                    findNavController().navigate(LogoutFragmentDirections.actionLogoutFragmentToWelcomeFragment())
                }
            }
        }
    }

}