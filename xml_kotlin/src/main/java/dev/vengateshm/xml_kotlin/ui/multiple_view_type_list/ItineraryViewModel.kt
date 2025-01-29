package dev.vengateshm.xml_kotlin.ui.multiple_view_type_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.vengateshm.xml_kotlin.utils.ItineraryConstants.ACTION_ADD_MORE

class ItineraryViewModel : ViewModel() {

    private val _itineraryList = MutableLiveData<List<ItineraryListItem>>()
    val itineraryList: LiveData<List<ItineraryListItem>> get() = _itineraryList

    init {
        loadItineraryData()
    }

    private fun loadItineraryData() {
        val itineraryItems = listOf(
            ItineraryListItem.HeaderItem("Day 1 - Arrival", "Welcome to Paris!"),
            ItineraryListItem.BodyItem(
                "Visit Eiffel Tower",
                "10:00 AM",
                "Champ de Mars, Paris",
            ),
            ItineraryListItem.BodyItem(
                "Lunch at Le Meurice",
                "1:00 PM",
                "228 Rue de Rivoli, Paris",
            ),
            ItineraryListItem.FooterItem("End of Day 1"),
        )
        _itineraryList.value = itineraryItems
    }

    fun onItemClicked(item: ItineraryListItem) {
        var clickEventValue = ""
        when (item) {
            is ItineraryListItem.BodyItem -> {

            }

            is ItineraryListItem.FooterItem -> {
                when (item.action) {
                    ACTION_ADD_MORE -> {
                        clickEventValue = "Add More"
                    }
                }
            }

            is ItineraryListItem.HeaderItem -> {

            }
        }
        if (clickEventValue.isNotEmpty()) {
            // Add event analytics
        }
    }

    fun addItineraryItem(newItem: ItineraryListItem) {
        val updatedList = _itineraryList.value.orEmpty().toMutableList()
        updatedList.add(newItem)
        _itineraryList.value = updatedList
    }
}
