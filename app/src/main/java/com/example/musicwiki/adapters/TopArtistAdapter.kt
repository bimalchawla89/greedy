package com.example.musicwiki.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.example.musicwiki.databinding.ArtistListItemBinding
import com.example.musicwiki.databinding.GenreDetailListItemBinding
import com.example.musicwiki.listener.ArtistClickListener
import com.example.musicwiki.models.ArtistInnerItem
import com.example.musicwiki.viewbinding.BindingViewHolder
import com.example.musicwiki.viewbinding.createBindingViewHolder

typealias TopArtistViewHolder = BindingViewHolder<ArtistListItemBinding>

class TopArtistAdapter(private val artistClickListener: ArtistClickListener) :
    ListAdapter<ArtistInnerItem, TopArtistViewHolder>(ArtistDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopArtistViewHolder {
        return createBindingViewHolder(parent)
    }

    override fun onBindViewHolder(holder: TopArtistViewHolder, position: Int) {
        val artist = getItem(position)
        holder.binding.tvTitle.text = artist.name
        holder.binding.tvDescription.text = artist.url
        holder.binding.ivIcon.load(artist.image[2].text)
        holder.itemView.setOnClickListener {
            artistClickListener.artistClicked(artist.name)
        }
    }
}