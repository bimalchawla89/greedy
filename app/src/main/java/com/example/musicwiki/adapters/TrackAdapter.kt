package com.example.musicwiki.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.example.musicwiki.databinding.GenreDetailListItemBinding
import com.example.musicwiki.listener.TrackClickListener
import com.example.musicwiki.models.TrackItem
import com.example.musicwiki.viewbinding.BindingViewHolder
import com.example.musicwiki.viewbinding.createBindingViewHolder

typealias TrackViewHolder = BindingViewHolder<GenreDetailListItemBinding>

class TrackAdapter(private val trackClickListener: TrackClickListener) :
    ListAdapter<TrackItem, TrackViewHolder>(TrackDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return createBindingViewHolder(parent)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track = getItem(position)
        holder.binding.tvTitle.text = track.name
        holder.binding.tvDescription.text = track.duration
        holder.binding.ivIcon.load(track.image[2].text)
        holder.itemView.setOnClickListener {
            trackClickListener.trackClicked(track.name)
        }
    }
}

class TrackDiffCallback : DiffUtil.ItemCallback<TrackItem>() {
    override fun areItemsTheSame(oldItem: TrackItem, newItem: TrackItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: TrackItem, newItem: TrackItem): Boolean {
        return oldItem == newItem
    }
}