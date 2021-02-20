package com.example.musicwiki.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.musicwiki.databinding.GenreListItemBinding
import com.example.musicwiki.listener.GenreClickListener
import com.example.musicwiki.models.Tag
import com.example.musicwiki.viewbinding.BindingViewHolder
import com.example.musicwiki.viewbinding.createBindingViewHolder


// Instead of creating all the ViewHolder classes and increasing our code size, we are now going
// to use generics. typealias allows this to be very clean
typealias GenreViewHolder = BindingViewHolder<GenreListItemBinding>

class GenreAdapter(private val genreClickListener: GenreClickListener) :
    ListAdapter<Tag, GenreViewHolder>(GenreDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return createBindingViewHolder(parent)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val tag = getItem(position)
        holder.binding.tag = tag
        holder.binding.executePendingBindings()
        holder.itemView.setOnClickListener {
            genreClickListener.genreClicked(tag.name)
        }
    }
}

class GenreDiffCallback : DiffUtil.ItemCallback<Tag>() {
    override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem == newItem
    }
}