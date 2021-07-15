package com.teamtuna.emotionaldiary.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teamtuna.emotionaldiary.entity.DailyEmotion
import com.teamtuna.emotionaldiary.presentation.R
import com.teamtuna.emotionaldiary.presentation.databinding.LayoutHitoryItemBinding

class HistoryAdapter : ListAdapter<DailyEmotion, HistoryAdapter.EmotionHolder>(
    object : DiffUtil.ItemCallback<DailyEmotion>() {
        override fun areItemsTheSame(oldItem: DailyEmotion, newItem: DailyEmotion): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: DailyEmotion, newItem: DailyEmotion): Boolean =
            oldItem == newItem
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmotionHolder {
        val binding: LayoutHitoryItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_hitory_item, parent, false
        )
        return EmotionHolder(binding)
    }

    override fun onBindViewHolder(holder: EmotionHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class EmotionHolder(val binding: LayoutHitoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dailyEmotion: DailyEmotion) {
            binding.dailyEmotion = dailyEmotion
            binding.executePendingBindings()
        }
    }
}
