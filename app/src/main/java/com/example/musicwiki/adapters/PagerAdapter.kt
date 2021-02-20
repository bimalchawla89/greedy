package com.example.musicwiki.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwiki.R
import kotlinx.android.synthetic.main.page_layout.view.*

class PagerAdapter(private val context: Context, private val words: List<String>) :
    RecyclerView.Adapter<PagerAdapter.PageHolder>() {


    inner class PageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.textView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder =
        PageHolder(LayoutInflater.from(context).inflate(R.layout.page_layout, parent, false))


    override fun onBindViewHolder(holder: PagerAdapter.PageHolder, position: Int) {
        holder.textView.text = words[position]
    }

    override fun getItemCount(): Int = words.size
}