package com.omarismayilov.movaapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.omarismayilov.movaapp.data.model.CastDTO
import com.omarismayilov.movaapp.data.model.GenreDTO
import com.omarismayilov.movaapp.databinding.ItemCastBinding
import com.omarismayilov.movaapp.databinding.ItemGenreBinding

class CastAdapter : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCastBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CastDTO) {
            with(binding){
                cast = item
                binding.executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCastBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cast = differ.currentList[position]
        holder.bind(cast)
    }

    object CastDiffUtilCallback : DiffUtil.ItemCallback<CastDTO>() {
        override fun areItemsTheSame(oldItem: CastDTO, newItem: CastDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CastDTO, newItem: CastDTO): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, CastDiffUtilCallback)
}