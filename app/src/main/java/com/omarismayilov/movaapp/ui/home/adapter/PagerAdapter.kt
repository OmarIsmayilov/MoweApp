package com.omarismayilov.movaapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.omarismayilov.movaapp.common.utils.DiffUtilCallback
import com.omarismayilov.movaapp.data.model.MovieResponseDTO
import com.omarismayilov.movaapp.databinding.ItemPagingBinding

class PagerAdapter : RecyclerView.Adapter<PagerAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(val binding: ItemPagingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieResponseDTO) {
            with(binding) {
                movie = item
                binding.executePendingBindings()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemPagingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.bind(movie)
    }


    val differ = AsyncListDiffer(this, DiffUtilCallback)


}