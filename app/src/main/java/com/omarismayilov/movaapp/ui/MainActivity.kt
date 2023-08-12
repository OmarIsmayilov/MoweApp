package com.omarismayilov.movaapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.common.utils.ValidationHelper
import com.omarismayilov.movaapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (!ValidationHelper(this).isInternetAvailable(this)) {
            showNoInternetDialog()
        }
        setBottomView()
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
                R.id.authFragment,R.id.movieListFragment
                -> binding.bottomMenu.visibility = View.GONE
                else -> binding.bottomMenu.visibility = View.VISIBLE
            }
        }

    }
    private fun showNoInternetDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("No Internet Connection")
            .setMessage("Please check your internet connection and try again.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                finish()
            }
        val dialog = builder.create()
        dialog.show()
    }

}