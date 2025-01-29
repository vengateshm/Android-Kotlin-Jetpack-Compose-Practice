package dev.vengateshm.xml_kotlin.ui.multiple_view_type_list

import androidx.recyclerview.widget.DiffUtil

class ItineraryDiffCallback(
    private val oldList: List<ItineraryListItem>,
    private val newList: List<ItineraryListItem>,
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
    ): Boolean = oldList[oldItemPosition].viewType == newList[newItemPosition].viewType

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
    ): Boolean = oldList[oldItemPosition] == newList[newItemPosition]
}
