package com.omarismayilov.movaapp.ui.home.adapters

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.common.utils.Constants.BASE_URL_IMAGE
import com.omarismayilov.movaapp.common.utils.Extensions.loadUrl
import com.omarismayilov.movaapp.data.dto.MovieResponseDTO
import com.omarismayilov.movaapp.databinding.ItemMovieBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    inner class MovieHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: MovieResponseDTO) {
            with(binding){
                movie = item
                ivMovie.loadUrl(item.posterPath)
                binding.executePendingBindings()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    object diffUtilCallBack : DiffUtil.ItemCallback<MovieResponseDTO>() {
        override fun areItemsTheSame(
            oldItem: MovieResponseDTO,
            newItem: MovieResponseDTO,
        ): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieResponseDTO,
            newItem: MovieResponseDTO,
        ): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,diffUtilCallBack)
}