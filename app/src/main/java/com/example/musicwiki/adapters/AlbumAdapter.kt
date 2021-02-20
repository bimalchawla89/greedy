package com.example.musicwiki.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.example.musicwiki.databinding.GenreDetailListItemBinding
import com.example.musicwiki.listener.AlbumCLickListener
import com.example.musicwiki.models.AlbumItem
import com.example.musicwiki.viewbinding.BindingViewHolder
import com.example.musicwiki.viewbinding.createBindingViewHolder

// Instead of creating all the ViewHolder classes and increasing our code size, we are now going
// to use generics. typealias allows this to be very clean
typealias AlbumViewHolder = BindingViewHolder<GenreDetailListItemBinding>

class AlbumAdapter(private val albumCLickListener: AlbumCLickListener) :
    ListAdapter<AlbumItem, AlbumViewHolder>(AlbumDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return createBindingViewHolder(parent)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        holder.binding.tvTitle.text = album.name
        holder.binding.tvDescription.text = album.artist.name
        holder.binding.ivIcon.load(album.image[2].text)
        holder.itemView.setOnClickListener {
            albumCLickListener.albumClicked(album.name, album.artist.name)
        }
    }
}

class AlbumDiffCallback : DiffUtil.ItemCallback<AlbumItem>() {
    override fun areItemsTheSame(oldItem: AlbumItem, newItem: AlbumItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: AlbumItem, newItem: AlbumItem): Boolean {
        return oldItem == newItem
    }
}