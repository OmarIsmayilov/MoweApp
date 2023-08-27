package com.omarismayilov.movaapp.ui.myList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.common.utils.Extensions.gone
import com.omarismayilov.movaapp.common.utils.Extensions.showMessage
import com.omarismayilov.movaapp.common.utils.Extensions.visible
import com.omarismayilov.movaapp.databinding.FragmentMyListBinding
import com.omarismayilov.movaapp.ui.detail.state.DetailUiState
import com.omarismayilov.movaapp.ui.myList.adapter.MyListAdapter
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class MyListFragment : BaseFragment<FragmentMyListBinding>(FragmentMyListBinding::inflate) {
    private val viewModel: MyListViewModel by viewModels()
    private val listAdapter = MyListAdapter()

    override fun observeEvents() {
        with(binding) {
            viewModel.listData.observe(viewLifecycleOwner) {
                when (it) {
                    is MyListUiState.SuccessListData -> {
                        loginLoading.gone()
                        if (it.data.isEmpty()) {
                            lyError.visible()
                            rvMyList.gone()
                        } else {
                            lyError.gone()
                            rvMyList.visible()
                        }
                        listAdapter.differ.submitList(it.data)
                    }

                    is MyListUiState.Loading -> {
                        loginLoading.visible()
                    }

                    is MyListUiState.Error -> {
                        loginLoading.gone()
                        requireActivity().showMessage(
                            it.message,
                            FancyToast.ERROR
                        )
                    }
                }
            }
        }
    }

    override fun onCreateFinish() {
        setAdapter()
    }

    private fun setAdapter() {
        binding.rvMyList.adapter = listAdapter
    }

    override fun setupListeners() {
        listAdapter.onClick = {
            findNavController().navigate(
                MyListFragmentDirections.actionMyListFragmentToDetailFragment(
                    it
                )
            )
        }
        listAdapter.onDelete = {
            viewModel.deleteItem(it)
        }
    }

}

