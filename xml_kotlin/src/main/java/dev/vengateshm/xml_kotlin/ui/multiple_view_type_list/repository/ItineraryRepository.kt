package dev.vengateshm.xml_kotlin.ui.multiple_view_type_list.repository

import androidx.lifecycle.LiveData
import dev.vengateshm.xml_kotlin.ui.multiple_view_type_list.db.ItineraryEntity
import dev.vengateshm.xml_kotlin.ui.multiple_view_type_list.domain.MOBItineraryModel

interface ItineraryRepository {
    suspend fun saveItinerary(itinerary: ItineraryEntity)
    fun getItinerary(id: Int): LiveData<MOBItineraryModel?>
}