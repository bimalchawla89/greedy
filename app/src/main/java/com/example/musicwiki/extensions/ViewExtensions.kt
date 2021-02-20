package com.example.musicwiki.extensions

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun ViewGroup.gone() {
    this.visibility = View.GONE
}

fun ViewGroup.visible() {
    this.visibility = View.VISIBLE
}

fun ViewGroup.gone(activity: Activity) {
    this.visibility = View.GONE
    activity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun ViewGroup.visible(activity: Activity) {
    this.visibility = View.VISIBLE
    activity.window.setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun TextView.text(id: Int) {
    this.text = context.getString(id)
}

fun RecyclerView.addDivider() {
//    this.addItemDecoration(
//        ListPaddingDecoration(
//            context, 0, 0
//        )
//    )
}