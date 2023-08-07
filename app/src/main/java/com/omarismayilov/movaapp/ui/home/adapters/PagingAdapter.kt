package com.omarismayilov.movaapp.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.common.utils.Constants.BASE_URL_IMAGE
import com.omarismayilov.movaapp.data.dto.MovieResponseDTO
import com.omarismayilov.movaapp.databinding.ItemPagingBinding
import java.util.Objects

class PagingAdapter(private val movies: List<MovieResponseDTO>) : RecyclerView.Adapter<PagingAdapter.MovieViewHolder>() {


    inner class MovieViewHolder(val binding:ItemPagingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResponseDTO) {
            with(binding){
                Glide.with(itemView.context)
                    .load("$BASE_URL_IMAGE${movie.backdropPath}")
                    .into(ivBackground)
                tvTitle.text = movie.title
                tvGenres.text = movie.overview
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemPagingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }



}