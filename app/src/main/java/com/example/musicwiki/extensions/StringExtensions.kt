package com.example.musicwiki.extensions

import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.getHtmlSpannedString(id: String): Spanned = id.toHtmlSpan()

fun Activity.getHtmlSpannedString(id: String): Spanned = id.toHtmlSpan()

fun Context.getHtmlSpannedString(id: String): Spanned = id.toHtmlSpan()

fun String.toHtmlSpan(): Spanned =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }