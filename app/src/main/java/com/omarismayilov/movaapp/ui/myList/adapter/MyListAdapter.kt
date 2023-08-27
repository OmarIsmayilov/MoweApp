package com.omarismayilov.movaapp.ui.myList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.omarismayilov.movaapp.data.model.local.MovieLocalModel
import com.omarismayilov.movaapp.databinding.ItemFavBinding


class MyListAdapter : RecyclerView.Adapter<MyListAdapter.MovieHolder>() {

    var onClick : (Int) -> Unit = {}
    var onDelete : (Int) -> Unit = {}

    inner class MovieHolder(val binding: ItemFavBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: MovieLocalModel) {
            with(binding){
                movie = item
                binding.executePendingBindings()

                itemView.setOnClickListener {
                    onClick(item.id)
                }
                ibDelete.setOnClickListener {
                    onDelete(item.id)
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = ItemFavBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    object ListDiffUtilCallback : DiffUtil.ItemCallback<MovieLocalModel>() {
        override fun areItemsTheSame(oldItem: MovieLocalModel, newItem: MovieLocalModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieLocalModel, newItem: MovieLocalModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, ListDiffUtilCallback)

}