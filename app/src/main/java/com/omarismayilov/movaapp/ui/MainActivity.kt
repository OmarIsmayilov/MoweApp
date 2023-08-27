package com.omarismayilov.movaapp.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.common.utils.ValidationHelper
import com.omarismayilov.movaapp.databinding.ActivityMainBinding
import com.omarismayilov.movaapp.ui.profile.ProfileViewModel
import com.thecode.aestheticdialogs.AestheticDialog
import com.thecode.aestheticdialogs.DialogAnimation
import com.thecode.aestheticdialogs.DialogStyle
import com.thecode.aestheticdialogs.DialogType
import com.thecode.aestheticdialogs.OnDialogClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTheme()
        if (!ValidationHelper(this).isInternetAvailable(this)) {
            showNoInternetDialog()
        }
        setBottomView()
    }

    private fun setTheme() {
        if(viewModel.getTheme()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }


    private fun setBottomView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomMenu, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment, R.id.loginFragment,
                R.id.registerFragment, R.id.welcomeFragment,
                R.id.authFragment, R.id.movieListFragment, R.id.youtubePlayerFragment, R.id.detailFragment,
                R.id.paymentFragment, R.id.editProfileFragment, R.id.addCardFragment, R.id.rateFragment,
                -> binding.bottomMenu.visibility = View.GONE

                else -> binding.bottomMenu.visibility = View.VISIBLE
            }
        }

    }

    private fun showNoInternetDialog() {
        AestheticDialog.Builder(this, DialogStyle.CONNECTIFY, DialogType.ERROR)
            .setTitle("No Internet Connection")
            .setMessage("Please check your connection")
            .setCancelable(false)
            .setDarkMode(
                AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES
            )
            .setGravity(Gravity.BOTTOM)
            .setAnimation(DialogAnimation.SHRINK)
            .setOnClickListener(object : OnDialogClickListener {
                override fun onClick(dialog: AestheticDialog.Builder) {
                    finish()
                }
            })
            .show()
    }

}