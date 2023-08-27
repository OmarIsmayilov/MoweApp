package com.omarismayilov.movaapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.omarismayilov.movaapp.R
import com.omarismayilov.movaapp.data.model.CastDTO
import com.omarismayilov.movaapp.data.model.ReviewDTO
import com.omarismayilov.movaapp.databinding.ItemCastBinding
import com.omarismayilov.movaapp.databinding.ItemReviewBinding

class ReviewAdapter: RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ReviewDTO) {
            with(binding){
                review = item
                if(item.authorDetails.avatarPath==null){
                  ivProfile.setImageResource(R.drawable.user)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemReviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = differ.currentList[position]
        holder.bind(review)
    }

    object ReviewDiffUtilCallback : DiffUtil.ItemCallback<ReviewDTO>() {
        override fun areItemsTheSame(oldItem: ReviewDTO, newItem: ReviewDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ReviewDTO, newItem: ReviewDTO): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, ReviewDiffUtilCallback)
}