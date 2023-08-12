package com.omarismayilov.movaapp.ui.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.databinding.FragmentFilterBinding
import com.omarismayilov.movaapp.databinding.FragmentLogoutBinding
import com.omarismayilov.movaapp.ui.auth.AuthViewModel


class FilterFragment : BottomSheetDialogFragment(R.layout.fragment_filter) {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        with(binding) {
            btnApply.setOnClickListener { dismiss() }
            btnReset.setOnClickListener { clearChip() }
        }
    }

    private fun clearChip() {
        binding.apply {
            cgCategories.clearCheck()
            cgGenre.clearCheck()
            cgRegion.clearCheck()
            cgSort.clearCheck()
            cgTime.clearCheck()
        }
    }

}