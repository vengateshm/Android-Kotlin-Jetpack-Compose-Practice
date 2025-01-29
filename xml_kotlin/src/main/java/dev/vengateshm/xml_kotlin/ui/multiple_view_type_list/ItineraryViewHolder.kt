package dev.vengateshm.xml_kotlin.ui.multiple_view_type_list

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.vengateshm.xml_kotlin.R
import dev.vengateshm.xml_kotlin.utils.ItineraryConstants.ACTION_ADD_MORE

sealed class ItineraryViewHolder(
    view: View,
    protected val clickListener: (ItineraryListItem) -> Unit = {},
) : RecyclerView.ViewHolder(view) {

    open fun bind(listItem: ItineraryListItem) {}

    class HeaderViewHolder(
        view: View,
        clickListener: (ItineraryListItem) -> Unit,
    ) : ItineraryViewHolder(view) {

        private val titleTextView: TextView = view.findViewById(R.id.tvHeaderTitle)
        private val subtitleTextView: TextView = view.findViewById(R.id.tvHeaderSubtitle)

        override fun bind(listItem: ItineraryListItem) {
            val item = (listItem as? ItineraryListItem.HeaderItem) ?: return
            titleTextView.text = item.title
            subtitleTextView.text = item.subtitle
        }
    }

    class BodyViewHolder(
        view: View,
        clickListener: (ItineraryListItem) -> Unit,
    ) : ItineraryViewHolder(view) {

        private val activityTextView: TextView = view.findViewById(R.id.tvActivityName)
        private val timeTextView: TextView = view.findViewById(R.id.tvActivityTime)
        private val locationTextView: TextView = view.findViewById(R.id.tvActivityLocation)

        override fun bind(listItem: ItineraryListItem) {
            val item = (listItem as? ItineraryListItem.BodyItem) ?: return
            activityTextView.text = item.activityName
            timeTextView.text = item.time
            locationTextView.text = item.location
        }
    }

    class FooterViewHolder(
        view: View,
        clickListener: (ItineraryListItem) -> Unit,
    ) : ItineraryViewHolder(view) {

        private val summaryTextView: TextView = view.findViewById(R.id.tvSummary)
        private val addMoreButton: Button = view.findViewById(R.id.btnAddMore)

        override fun bind(listItem: ItineraryListItem) {
            val item = (listItem as? ItineraryListItem.FooterItem) ?: return
            summaryTextView.text = item.summary
            addMoreButton.setOnClickListener {
                clickListener.invoke(listItem.copy(action = ACTION_ADD_MORE))
            }
        }
    }
}
