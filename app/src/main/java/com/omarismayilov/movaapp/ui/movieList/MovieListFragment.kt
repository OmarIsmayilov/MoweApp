package com.omarismayilov.movaapp.ui.movieList

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.common.utils.MovieTypeEnum
import com.omarismayilov.movaapp.databinding.FragmentMovieListBinding
import com.omarismayilov.movaapp.ui.movieList.adapter.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment :
    BaseFragment<FragmentMovieListBinding>(FragmentMovieListBinding::inflate) {
    private val args: MovieListFragmentArgs by navArgs()
    private val viewModel: MovieListViewModel by viewModels()
    private val adapter = MovieListAdapter()

    override fun observeEvents() {
        with(binding) {
            with(viewModel) {
                movieData.observe(viewLifecycleOwner) { adapter.differ.submitList(it) }
                }
            }
        }


    override fun onCreateFinish() {
        setRecyclerView()
        val movieType = args.movieType
        binding.materialToolbar.title = movieType.title
        when (movieType.type) {
            MovieTypeEnum.TOP_RATED_MOVIES -> viewModel.getTrending()
            MovieTypeEnum.UPCOMING -> viewModel.getUpcoming()
        }
    }

    private fun setRecyclerView() {
        binding.rvMovieList.adapter = adapter
    }

    override fun setupListeners() {
        binding.materialToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

}