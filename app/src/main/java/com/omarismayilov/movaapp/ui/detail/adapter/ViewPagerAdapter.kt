package com.omarismayilov.movaapp.ui.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.omarismayilov.movaapp.ui.auth.LoginFragment
import com.omarismayilov.movaapp.ui.detail.CommentFragment
import com.omarismayilov.movaapp.ui.detail.RecommendationFragment
import com.omarismayilov.movaapp.ui.detail.TrailerFragment


class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val id: Int
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TrailerFragment(id)
            1 -> RecommendationFragment(id)
            else -> CommentFragment(id)
        }
    }

}