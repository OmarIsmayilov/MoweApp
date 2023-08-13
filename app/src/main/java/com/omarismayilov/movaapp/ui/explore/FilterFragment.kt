package com.omarismayilov.movaapp.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.data.model.response.FilterOption
import com.omarismayilov.movaapp.databinding.FragmentFilterBinding


class FilterFragment : BottomSheetDialogFragment(R.layout.fragment_filter) {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!
    private val args = Bundle()


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
            btnApply.setOnClickListener {
                args.putParcelable("filterOptions",getFilterOptions())
                findNavController().navigate(R.id.exploreFragment,args)
            }
            btnReset.setOnClickListener { clearChip() }
        }
    }

    private fun getFilterOptions(): FilterOption {
        with(binding) {
            val selectedGenres = getSelectedChipTexts(cgGenre)
            val selectedCategory = getSelectedChipText(cgCategories)
            val selectedRegion = getSelectedChipText(cgRegion)
            val selectedPeriod = getSelectedChipText(cgTime)
            val selectedSort = getSelectedChipText(cgSort)


            return FilterOption(
                genre = selectedGenres,
                category = selectedCategory,
                region = selectedRegion,
                period = selectedPeriod,
                sort = selectedSort
            )
        }
    }

    private fun getSelectedChipTexts(chipGroup: ChipGroup): List<String> {
        val selectedChipTexts = mutableListOf<String>()
        for (i in 0 until chipGroup.childCount) {
            val chip = chipGroup.getChildAt(i) as Chip
            if (chip.isChecked) {
                selectedChipTexts.add(chip.text.toString())
            }
        }
        return selectedChipTexts
    }

    private fun getSelectedChipText(chipGroup: ChipGroup): String {
        var text = ""
        for (i in 0 until chipGroup.childCount) {
            val chip = chipGroup.getChildAt(i) as Chip
            if (chip.isChecked) {
                text = chip.text.toString()
            }
        }
        return text
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