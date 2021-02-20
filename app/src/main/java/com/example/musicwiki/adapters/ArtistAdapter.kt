package com.example.musicwiki.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.example.musicwiki.databinding.GenreDetailListItemBinding
import com.example.musicwiki.listener.ArtistClickListener
import com.example.musicwiki.models.ArtistInnerItem
import com.example.musicwiki.viewbinding.BindingViewHolder
import com.example.musicwiki.viewbinding.createBindingViewHolder

typealias ArtistViewHolder = BindingViewHolder<GenreDetailListItemBinding>

class ArtistAdapter(private val artistClickListener: ArtistClickListener) :
    ListAdapter<ArtistInnerItem, ArtistViewHolder>(ArtistDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return createBindingViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = getItem(position)
        holder.binding.tvTitle.text = artist.name
        holder.binding.tvDescription.text = artist.url
        holder.binding.ivIcon.load(artist.image[2].text)
        holder.itemView.setOnClickListener {
            artistClickListener.artistClicked(artist.name)
        }
    }
}

class ArtistDiffCallback : DiffUtil.ItemCallback<ArtistInnerItem>() {
    override fun areItemsTheSame(oldItem: ArtistInnerItem, newItem: ArtistInnerItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ArtistInnerItem, newItem: ArtistInnerItem): Boolean {
        return oldItem == newItem
    }
}