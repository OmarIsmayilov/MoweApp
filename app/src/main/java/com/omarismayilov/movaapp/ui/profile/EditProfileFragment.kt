package com.omarismayilov.movaapp.ui.profile

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.common.utils.Extensions.showMessage
import com.omarismayilov.movaapp.databinding.FragmentEditProfileBinding
import com.omarismayilov.movaapp.ui.auth.AuthViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment :
    BaseFragment<FragmentEditProfileBinding>(FragmentEditProfileBinding::inflate) {
    private val viewModel: ProfileViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModels()

    override fun observeEvents() {

    }

    override fun onCreateFinish() {
        setData()
        viewModel.getPhoto()?.let {
            binding.ivProfile.setImageURI(it)
        } ?: binding.ivProfile.setImageResource(R.drawable.example)

    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.lyProfilePhoto.setOnClickListener {
            getPhoto()
        }
    }

    private fun setData() {
        with(binding){
            val user = authViewModel.getUserInfo()
            user?.let {
                etName.setText(it.email?.substringBefore("@")?.capitalize())
                etMail.setText(it.email)
            }
        }
    }

    private fun getPhoto() {
        ImagePicker.with(requireActivity())
            .crop()
            .compress(1024)
            .maxResultSize(1080, 1080)
            .createIntent { intent ->
                startForProfileImageResult.launch(intent)
            }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                data?.data?.let {
                    binding.ivProfile.setImageURI(it)
                    viewModel.savePhoto(it.toString())
                }
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                requireActivity().showMessage(ImagePicker.getError(data), FancyToast.ERROR)
            }
        }

}