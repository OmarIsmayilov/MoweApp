package com.omarismayilov.movaapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.omarismayilov.movaapp.data.model.GenreDTO
import com.omarismayilov.movaapp.databinding.ItemGenreBinding

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemGenreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GenreDTO) {
            with(binding){
                tvGenre.text = " ${item.name}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemGenreBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = differ.currentList[position]
        holder.bind(genre)
    }

    object GenreDiffUtilCallback : DiffUtil.ItemCallback<GenreDTO>() {
        override fun areItemsTheSame(oldItem: GenreDTO, newItem: GenreDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GenreDTO, newItem: GenreDTO): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, GenreDiffUtilCallback)
}