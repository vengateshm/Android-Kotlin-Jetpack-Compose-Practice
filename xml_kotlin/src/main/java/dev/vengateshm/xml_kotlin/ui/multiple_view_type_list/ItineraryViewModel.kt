package dev.vengateshm.xml_kotlin.ui.multiple_view_type_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dev.vengateshm.xml_kotlin.ui.multiple_view_type_list.db.ItineraryEntity
import dev.vengateshm.xml_kotlin.ui.multiple_view_type_list.domain.MOBItineraryModel
import dev.vengateshm.xml_kotlin.ui.multiple_view_type_list.repository.ItineraryRepository
import dev.vengateshm.xml_kotlin.utils.ItineraryConstants.ACTION_ADD_MORE
import kotlinx.coroutines.launch

class ItineraryViewModel(private val repository: ItineraryRepository) : ViewModel() {

    private val _itineraryList = MutableLiveData<List<ItineraryListItem>>()
    val itineraryList: LiveData<List<ItineraryListItem>> get() = _itineraryList

    init {
//        loadItineraryData()
        viewModelScope.launch {
            //addEntity()
            getItinerary(id = 1)
        }
    }

    private suspend fun addEntity() {
        val newEntity = ItineraryEntity(
            id = 1,
            jsonResponse = """
        {
          "title": "Weekend Trip to Paris",
          "description": "A fun-filled weekend exploring the best of Paris. Includes activities like sightseeing, dining, and more.",
          "activities": [
            {
              "name": "Eiffel Tower Visit",
              "time": "10:00 AM",
              "location": "Eiffel Tower, Paris"
            },
            {
              "name": "Louvre Museum Tour",
              "time": "01:00 PM",
              "location": "Louvre Museum, Paris"
            },
            {
              "name": "Seine River Cruise",
              "time": "04:00 PM",
              "location": "Seine River, Paris"
            }
          ]
        }
    """,
        )
        repository.saveItinerary(newEntity) // Repository function to add the entity to Room
    }

    private fun getItinerary(id: Int): LiveData<List<ItineraryListItem>?> {
        return repository.getItinerary(id).map { it?.toItineraryItems(it) }.apply {
            value?.let {
                _itineraryList.value = it
            }
        }
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

    private fun MOBItineraryModel.toItineraryItems(model: MOBItineraryModel): List<ItineraryListItem> {
        val items = mutableListOf<ItineraryListItem>()
        items.add(ItineraryListItem.HeaderItem(model.title, model.description))
        model.activities.forEach {
            items.add(ItineraryListItem.BodyItem(it.name, it.time, it.location))
        }
        items.add(ItineraryListItem.FooterItem("End of the itinerary"))
        return items
    }
}
