package dev.vengateshm.xml_kotlin.ui.multiple_view_type_list.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.google.gson.Gson
import dev.vengateshm.xml_kotlin.ui.multiple_view_type_list.db.ItineraryDao
import dev.vengateshm.xml_kotlin.ui.multiple_view_type_list.db.ItineraryEntity
import dev.vengateshm.xml_kotlin.ui.multiple_view_type_list.domain.MOBItineraryModel
import dev.vengateshm.xml_kotlin.utils.fromJson

class ItineraryRepositoryImpl(private val dao: ItineraryDao) : ItineraryRepository {
    override suspend fun saveItinerary(itinerary: ItineraryEntity) {
        dao.insert(itinerary)
    }

    override fun getItinerary(id: Int): LiveData<MOBItineraryModel?> {
        return dao.getItineraryLiveDataById(id).map {
            Gson().fromJson<MOBItineraryModel>(it.firstOrNull()?.jsonResponse.orEmpty())
        }
    }
}