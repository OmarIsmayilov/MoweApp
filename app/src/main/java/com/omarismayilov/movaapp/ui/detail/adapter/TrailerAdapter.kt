package com.omarismayilov.movaapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.omarismayilov.movaapp.data.model.TrailerDTO
import com.omarismayilov.movaapp.databinding.ItemTrailerBinding

class TrailerAdapter : RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>() {

    var onClick: (id: String) -> Unit = {}

    inner class TrailerViewHolder(private val binding: ItemTrailerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TrailerDTO) {
            binding.trailer = item
            binding.executePendingBindings()

            itemView.setOnClickListener {
                item.key?.let { it1 -> onClick(it1) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val view = ItemTrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrailerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    object TrailerDiffUtilCallback : DiffUtil.ItemCallback<TrailerDTO>() {
        override fun areItemsTheSame(oldItem: TrailerDTO, newItem: TrailerDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TrailerDTO, newItem: TrailerDTO): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, TrailerDiffUtilCallback)
}