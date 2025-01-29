package dev.vengateshm.xml_kotlin.utils

import android.view.View
import android.widget.TextView

fun TextView.bindOrHideWhenNull(text: String?) {
    this.text = text ?: ""
    this.visibility = if (text.isNullOrEmpty()) View.GONE else View.VISIBLE
}