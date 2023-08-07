package com.omarismayilov.movaapp.ui.home

import android.content.ContentValues.TAG
import android.graphics.Movie
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.PagerAdapter
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.data.network.NetworkResponse
import com.omarismayilov.movaapp.databinding.FragmentHomeBinding
import com.omarismayilov.movaapp.ui.home.adapters.MovieAdapter
import com.omarismayilov.movaapp.ui.home.adapters.PagingAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel : HomeViewModel by viewModels()
    private val movieAdapter = MovieAdapter()
    override fun observeEvents() {
        with(viewModel){
            nowPlayingMovies.observe(viewLifecycleOwner){response->
                when(response){
                    is NetworkResponse.Success ->{
                        Log.e(TAG, "observeEvents:${response.data.movieResponseDTOS} ", )
                        val adapter = PagingAdapter(response.data.movieResponseDTOS)
                        binding.viewPager.adapter = adapter
                        movieAdapter.differ.submitList(response.data.movieResponseDTOS)
                    }
                    is NetworkResponse.Error ->{
                        Log.e(TAG, "observeEventsError: ${response.exception}", )
                    }
                    is NetworkResponse.Loading ->{
                        Log.e(TAG, "observeEvents:Loading $response", )
                    }
                    else->{}
                }
            }


        }
    }

    override fun onCreateFinish() {
        setRecyclerView()
    }

    private fun setRecyclerView() {
        with(binding){
            recyclerView.adapter = movieAdapter
        }
    }

    override fun setupListeners() {

    }


}