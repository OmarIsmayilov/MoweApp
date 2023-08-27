package com.omarismayilov.movaapp.ui.explore

import android.content.ContentValues.TAG
import android.util.Log
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.common.utils.Extensions.gone
import com.omarismayilov.movaapp.common.utils.Extensions.visible
import com.omarismayilov.movaapp.data.model.response.FilterOption
import com.omarismayilov.movaapp.databinding.FragmentExploreBinding
import com.omarismayilov.movaapp.ui.explore.adapter.ExploreAdapter
import com.omarismayilov.movaapp.ui.explore.adapter.SearchAdapter
import com.omarismayilov.movaapp.ui.home.MovieUiState
import com.omarismayilov.movaapp.ui.movieList.MovieListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding>(FragmentExploreBinding::inflate) {

    private val viewModel: ExploreViewModel by viewModels()
    private val exploreAdapter = ExploreAdapter()
    private val searchAdapter = SearchAdapter()

    override fun observeEvents() {
        with(viewModel) {
            with(binding) {
                exploreData.observe(viewLifecycleOwner) {
                    when (it) {
                        is MovieUiState.Success -> {
                            loginLoading4.gone()
                            lyError.gone()
                            exploreAdapter.differ.submitList(it.data)
                        }

                        is MovieUiState.Error -> {
                            loginLoading4.gone()
                            lyError.visible()
                            tvError.text = it.message
                        }

                        is MovieUiState.Loading -> {
                            loginLoading4.visible()
                        }
                    }
                }

                searchData.observe(viewLifecycleOwner) {
                    when (it) {
                        is MovieUiState.Success -> {
                            loginLoading4.gone()
                            if (it.data.isEmpty()) {
                                lyError.visible()
                                tvError.text = getString(R.string.error_message)
                            } else {
                                lyError.gone()
                            }
                            searchAdapter.differ.submitList(it.data)

                        }

                        is MovieUiState.Error -> {
                            loginLoading4.gone()
                            lyError.visible()
                            tvError.text = it.message
                        }

                        is MovieUiState.Loading -> {
                            loginLoading4.visible()
                        }
                    }
                }

            }
        }
    }

    override fun onCreateFinish() {
        val argValue = arguments?.getParcelable<FilterOption>("filterOptions")
        setAdapters()
        argValue?.let {
            filterMovie(it)
        }


    }

    private fun filterMovie(filterOption: FilterOption) {
        viewModel.getFilter(filterOption)
        Log.e(TAG, "filterMovie: $filterOption")
    }

    private fun setAdapters() {
        with(binding) {
            rvExplore.adapter = exploreAdapter
            rvSearch.adapter = searchAdapter
        }
    }


    override fun setupListeners() {
        with(binding) {

            ibFilter.setOnClickListener {
                findNavController().navigate(ExploreFragmentDirections.actionExploreFragmentToFilterFragment())
            }

            etSearch.setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { viewModel.getSearch(it) }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return if (newText?.length == 0) {
                        lyExplore.visible()
                        lySearch.gone()
                        lyError.gone()
                        false
                    } else {
                        lyExplore.gone()
                        lyError.gone()
                        lySearch.visible()
                        true
                    }
                }
            })

            searchAdapter.onCLick = {
                findNavController().navigate(
                    ExploreFragmentDirections.actionExploreFragmentToDetailFragment(
                        it
                    )
                )
            }

            exploreAdapter.onCLick = {
                findNavController().navigate(
                    ExploreFragmentDirections.actionExploreFragmentToDetailFragment(
                        it
                    )
                )
            }


        }
    }

}