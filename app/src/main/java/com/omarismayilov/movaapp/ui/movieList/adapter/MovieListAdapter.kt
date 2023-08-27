package com.omarismayilov.movaapp.ui.movieList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.omarismayilov.movaapp.common.utils.DiffUtilCallback
import com.omarismayilov.movaapp.data.model.MovieResponseDTO
import com.omarismayilov.movaapp.databinding.ItemMovieBinding
import com.omarismayilov.movaapp.databinding.ItemMovieListBinding

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {

     var onCLick: (id: Int) -> Unit = {}

    inner class MovieHolder(val binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieResponseDTO) {
            with(binding) {
                movie = item
                ratingBar.rating = item.voteAverage.toFloat()
                binding.executePendingBindings()

                itemView.setOnClickListener {
                    onCLick(item.id)
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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