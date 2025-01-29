package dev.vengateshm.xml_kotlin.ui.multiple_view_type_list.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itinerary_table")
data class ItineraryEntity(
    @PrimaryKey val id: Int,
    val jsonResponse: String, // JSON String containing itinerary details
)
