package com.omarismayilov.movaapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.omarismayilov.movaapp.common.utils.DiffUtilCallback
import com.omarismayilov.movaapp.common.utils.Extensions.loadUrl
import com.omarismayilov.movaapp.data.model.MovieResponseDTO
import com.omarismayilov.movaapp.databinding.ItemMovieBinding

class TrendingAdapter : RecyclerView.Adapter<TrendingAdapter.MovieHolder>() {

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

    val differ = AsyncListDiffer(this, DiffUtilCallback)

}