package dev.vengateshm.xml_kotlin.ui.multiple_view_type_list

import androidx.annotation.DimenRes

interface OffsetProvider {
    @DimenRes
    fun getDecorationTopOffset(adapterPosition: Int): Int

    @DimenRes
    fun getDecorationBottomOffset(adapterPosition: Int): Int

    @DimenRes
    fun getDecorationStartOffset(adapterPosition: Int): Int

    @DimenRes
    fun getDecorationEndOffset(adapterPosition: Int): Int
}