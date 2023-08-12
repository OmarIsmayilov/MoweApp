package com.omarismayilov.movaapp.ui.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.common.utils.Extensions.gone
import com.omarismayilov.movaapp.common.utils.Extensions.showMessage
import com.omarismayilov.movaapp.common.utils.Extensions.visible
import com.omarismayilov.movaapp.common.utils.MovieTypeEnum
import com.omarismayilov.movaapp.data.model.response.MovieType
import com.omarismayilov.movaapp.databinding.FragmentHomeBinding
import com.omarismayilov.movaapp.ui.home.adapter.TrendingAdapter
import com.omarismayilov.movaapp.ui.home.adapter.PagerAdapter
import com.omarismayilov.movaapp.ui.home.adapter.UpcomingAdapter
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    private val trendingAdapter = TrendingAdapter()
    private val upComingAdapter = UpcomingAdapter()
    private val pagerAdapter = PagerAdapter()
    override fun observeEvents() {
        with(viewModel) {
            with(binding) {

                nowPlayingMovies.observe(viewLifecycleOwner) {
                    when (it) {
                        is MovieUiState.Success -> {
                            loginLoading.gone()
                            pagerAdapter.differ.submitList(it.data)
                        }

                        is MovieUiState.Error -> {
                            loginLoading.gone()
                            requireActivity().showMessage("Error", it.message, MotionToastStyle.ERROR)
                        }
                        is MovieUiState.Loading -> {
                            loginLoading.visible()
                        }
                    }
                }

                topRatedMovies.observe(viewLifecycleOwner) {
                    when (it) {
                        is MovieUiState.Success -> {
                            trendingAdapter.differ.submitList(it.data.subList(0, 10))
                        }
                        is MovieUiState.Error -> {
                            requireActivity().showMessage("Error", it.message, MotionToastStyle.ERROR)
                        }
                        is MovieUiState.Loading -> {}
                    }
                }

                upComingMovies.observe(viewLifecycleOwner) {
                    when (it) {
                        is MovieUiState.Success -> {
                            upComingAdapter.differ.submitList(it.data)
                        }
                        is MovieUiState.Error -> {
                            requireActivity().showMessage("Error", it.message, MotionToastStyle.ERROR)
                        }
                        is MovieUiState.Loading -> {}
                    }
                }
            }
        }
    }

    override fun onCreateFinish() {
        setRecyclerView()
    }


    private fun setRecyclerView() {
        with(binding) {
            rvRated.adapter = trendingAdapter
            rvUpComing.adapter = upComingAdapter
            viewPager.adapter = pagerAdapter

        }
    }

    override fun setupListeners() {
        with(binding) {
            tvShow.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToMovieListFragment(
                        MovieType("Top 10 Movies This Week", MovieTypeEnum.TOP_RATED_MOVIES)
                    )
                )
            }
            tvShow2.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToMovieListFragment(
                        MovieType("New Releases", MovieTypeEnum.UPCOMING)
                    )
                )
            }
        }
    }
}

