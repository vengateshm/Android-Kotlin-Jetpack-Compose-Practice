package dev.vengateshm.xml_kotlin.ui.multiple_view_type_list.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItineraryDao {

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insert(itineraryEntity: ItineraryEntity)

    @Query("SELECT * FROM itinerary_table WHERE id = :id LIMIT 1")
    fun getItineraryLiveDataById(id: Int): LiveData<List<ItineraryEntity>>
}
