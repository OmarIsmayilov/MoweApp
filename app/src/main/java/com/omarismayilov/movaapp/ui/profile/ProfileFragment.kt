package com.omarismayilov.movaapp.ui.profile


import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.databinding.FragmentProfileBinding


class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun observeEvents() {


    }

    override fun onCreateFinish() {
        setTheme()
    }

    override fun setupListeners() {
        with(binding) {
            logoutLy.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLogoutFragment())
            }
            themeSwitch.setOnCheckedChangeListener { _, checked ->
                if (checked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
    }

    private fun setTheme() {
        val theme = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO
        binding.themeSwitch.isChecked = !theme
    }
}