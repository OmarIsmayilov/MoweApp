package com.omarismayilov.movaapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.omarismayilov.movaapp.common.utils.DiffUtilCallback
import com.omarismayilov.movaapp.data.model.MovieResponseDTO
import com.omarismayilov.movaapp.databinding.ItemExploreBinding
import com.omarismayilov.movaapp.databinding.ItemMovieListBinding

class RecommendationAdapter :
    RecyclerView.Adapter<RecommendationAdapter.RecommendationsViewHolder>() {

    var onCLick: (Int) -> Unit = {}

    inner class RecommendationsViewHolder(private val binding: ItemExploreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieResponseDTO) {
            binding.movie = item

            itemView.setOnClickListener {
                onCLick(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationsViewHolder {
        val layout = ItemExploreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendationsViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecommendationsViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    val differ = AsyncListDiffer(this, DiffUtilCallback)
}