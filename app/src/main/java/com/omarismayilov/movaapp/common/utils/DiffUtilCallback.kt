package com.omarismayilov.movaapp.common.utils

import androidx.recyclerview.widget.DiffUtil
import com.omarismayilov.movaapp.data.model.MovieResponseDTO

object DiffUtilCallback : DiffUtil.ItemCallback<MovieResponseDTO>() {
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