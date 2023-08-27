package com.omarismayilov.movaapp.ui.detail

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.common.utils.Extensions.gone
import com.omarismayilov.movaapp.common.utils.Extensions.showMessage
import com.omarismayilov.movaapp.common.utils.Extensions.toLocalModel
import com.omarismayilov.movaapp.common.utils.Extensions.visible
import com.omarismayilov.movaapp.data.model.DetailDTO
import com.omarismayilov.movaapp.databinding.FragmentDetailBinding
import com.omarismayilov.movaapp.ui.detail.adapter.CastAdapter
import com.omarismayilov.movaapp.ui.detail.adapter.GenreAdapter
import com.omarismayilov.movaapp.ui.detail.adapter.ViewPagerAdapter
import com.omarismayilov.movaapp.ui.detail.state.DetailUiState
import com.omarismayilov.movaapp.ui.myList.MyListViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args: DetailFragmentArgs by navArgs()
    private val detailViewModel: DetailViewModel by viewModels()
    private val myListViewModel: MyListViewModel by viewModels()
    private val genreAdapter = GenreAdapter()
    private val castAdapter = CastAdapter()
    private lateinit var cMovie: DetailDTO

    override fun observeEvents() {
        with(detailViewModel) {
            with(binding) {
                detailData.observe(viewLifecycleOwner) {
                    when (it) {
                        is DetailUiState.SuccessDetailData -> {
                            lyMain.alpha = 1f
                            loginLoading.gone()
                            setData(it.data)
                        }

                        is DetailUiState.Loading -> {
                            loginLoading.visible()
                            lyMain.alpha = 0.4f
                        }

                        is DetailUiState.Error -> {
                            lyMain.alpha = 1f
                            loginLoading.gone()
                            requireActivity().showMessage(
                                it.message,
                                FancyToast.ERROR
                            )
                        }

                        is DetailUiState.SuccessCastData -> {}
                    }
                }

                castData.observe(viewLifecycleOwner) {
                    when (it) {
                        is DetailUiState.SuccessCastData -> {
                            castAdapter.differ.submitList(it.data)
                        }
                        is DetailUiState.Loading -> {}
                        is DetailUiState.Error -> {
                            requireActivity().showMessage(
                                it.message,
                                FancyToast.ERROR
                            )
                        }

                        is DetailUiState.SuccessDetailData -> {}
                    }
                }
            }
        }
    }

    private fun setData(data: DetailDTO) {
        binding.movie = data
        cMovie = data
        genreAdapter.differ.submitList(data.genreDTOS)
    }

    override fun onCreateFinish() {
        setAdapters()
        val id = args.id
        detailViewModel.getDetail(id)
        detailViewModel.getCredits(id)

    }

    private fun setAdapters() {
        with(binding) {
            rvGenre.adapter = genreAdapter
            rvCast.adapter = castAdapter
            detailViewPager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle, args.id)

            val tabsName = arrayOf("Trailers", "More Like This", "Comments")
            TabLayoutMediator(tabLayoutMovieDetail, detailViewPager) { tab, position ->
                tab.text = tabsName[position]
            }.attach()
        }
    }

    override fun setupListeners() {
        with(binding) {
            ibBack.setOnClickListener {
                findNavController().popBackStack()
            }
            ibAddList.setOnClickListener {
                myListViewModel.addList(
                    cMovie.toLocalModel()
                )
                requireActivity().showMessage("Succesfully added to your list",FancyToast.SUCCESS)
            }

            ibShare.setOnClickListener {
                shareMovie()
            }

            ibRate.setOnClickListener {
                findNavController().navigate(
                    DetailFragmentDirections.actionDetailFragmentToRateFragment(
                        cMovie.id,
                        cMovie.voteAverage.toFloat(),
                        cMovie.voteCount.toString()
                    )
                )
            }
        }
    }

    private fun shareMovie() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, cMovie.title)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, null))
    }

}