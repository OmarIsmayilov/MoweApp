package com.omarismayilov.movaapp.ui.profile


import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.common.utils.SharedPrefManager
import com.omarismayilov.movaapp.databinding.FragmentProfileBinding
import com.omarismayilov.movaapp.ui.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    private val viewModel: ProfileViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModels()

    override fun observeEvents() {}

    override fun onCreateFinish() {
        setTheme()
        setData()
        viewModel.getPhoto()?.let {
            binding.ivPhoto.setImageURI(it)
        } ?: binding.ivPhoto.setImageResource(R.drawable.example)
    }

    private fun setData() {
        with(binding){
            val user = authViewModel.getUserInfo()
            user?.let {
                tvName.text = it.email?.substringBefore("@")?.capitalize()
                tvMail.text = it.email
            }
        }
    }

    override fun setupListeners() {
        with(binding) {
            logoutLy.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLogoutFragment())
            }
            lyPayment.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToPaymentFragment())
            }
            lyEdit.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment())
            }
            lyPhoto.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment())
            }
            themeSwitch.setOnCheckedChangeListener { _, checked ->
                if (checked) {
                    viewModel.saveTheme(true)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    viewModel.saveTheme(false)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }

        }
    }

    private fun setTheme() {
        binding.themeSwitch.isChecked =
            AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
    }
}