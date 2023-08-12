package com.omarismayilov.movaapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omarismayilov.movaapp.common.utils.Constants.BASE_URL_IMAGE
import com.omarismayilov.movaapp.common.utils.DiffUtilCallback
import com.omarismayilov.movaapp.common.utils.Extensions.loadUrl
import com.omarismayilov.movaapp.data.model.MovieResponseDTO
import com.omarismayilov.movaapp.databinding.ItemPagingBinding

class PagerAdapter : RecyclerView.Adapter<PagerAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(val binding: ItemPagingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResponseDTO) {
            with(binding) {
                ivBackground.loadUrl(movie.backdropPath)
                tvTitle.text = movie.title
                tvGenres.text = movie.overview
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