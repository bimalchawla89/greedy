package com.example.musicwiki.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.musicwiki.databinding.AlbumGenreListItemBinding
import com.example.musicwiki.databinding.GenreListItemBinding
import com.example.musicwiki.listener.GenreClickListener
import com.example.musicwiki.models.Tag
import com.example.musicwiki.viewbinding.BindingViewHolder
import com.example.musicwiki.viewbinding.createBindingViewHolder


// Instead of creating all the ViewHolder classes and increasing our code size, we are now going
// to use generics. typealias allows this to be very clean
typealias AlbumGenreViewHolder = BindingViewHolder<AlbumGenreListItemBinding>

class AlbumGenreAdapter(private val genreClickListener: GenreClickListener) :
    ListAdapter<Tag, AlbumGenreViewHolder>(GenreDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumGenreViewHolder {
        return createBindingViewHolder(parent)
    }

    override fun onBindViewHolder(holder: AlbumGenreViewHolder, position: Int) {
        val tag = getItem(position)
        holder.binding.tag = tag
        holder.binding.executePendingBindings()
        holder.itemView.setOnClickListener {
            genreClickListener.genreClicked(tag.name)
        }
    }
}