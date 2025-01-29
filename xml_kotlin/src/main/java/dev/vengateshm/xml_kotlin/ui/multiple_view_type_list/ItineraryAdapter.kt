package dev.vengateshm.xml_kotlin.ui.multiple_view_type_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.vengateshm.xml_kotlin.R

class ItineraryAdapter(
    private val listItems: MutableList<ItineraryListItem> = mutableListOf(),
    private val clickListener: (ItineraryListItem) -> Unit,
) : RecyclerView.Adapter<ItineraryViewHolder>(), OffsetProvider {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ItineraryViewHolder {
        return when (ItineraryListItemType.entries[viewType]) {
            ItineraryListItemType.HEADER -> {
                ItineraryViewHolder.HeaderViewHolder(
                    getView(parent, R.layout.item_itinerary_header),
                    clickListener,
                )
            }

            ItineraryListItemType.BODY -> {
                ItineraryViewHolder.BodyViewHolder(
                    getView(parent, R.layout.item_itinerary_body),
                    clickListener,
                )
            }

            ItineraryListItemType.FOOTER -> {
                ItineraryViewHolder.FooterViewHolder(
                    getView(parent, R.layout.item_itinerary_footer),
                    clickListener,
                )
            }
        }
    }

    override fun onBindViewHolder(
        holder: ItineraryViewHolder,
        position: Int,
    ) {
        holder.bind(listItems[position])
    }

    override fun getItemViewType(position: Int) = listItems[position].viewType.ordinal

    override fun getItemCount() = listItems.size

    fun setItineraryItems(newItems: List<ItineraryListItem>) {
        val diffResult = DiffUtil.calculateDiff(ItineraryDiffCallback(listItems, newItems))
        listItems.clear()
        listItems.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    fun getView(parent: ViewGroup, @LayoutRes layoutId: Int) =
        LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

    override fun getDecorationTopOffset(adapterPosition: Int) = R.dimen.common_ui_spacing_xxlg

    override fun getDecorationBottomOffset(adapterPosition: Int) =
        R.dimen.common_ui_spacing_no_space

    override fun getDecorationStartOffset(adapterPosition: Int) = R.dimen.common_ui_spacing_no_space

    override fun getDecorationEndOffset(adapterPosition: Int) = R.dimen.common_ui_spacing_no_space
}