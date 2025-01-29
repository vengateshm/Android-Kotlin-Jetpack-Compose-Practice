package dev.vengateshm.xml_kotlin.ui.multiple_view_type_list.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ItineraryEntity::class], version = 1)
abstract class ItineraryDatabase : RoomDatabase() {

    abstract fun itineraryDao(): ItineraryDao

    companion object {
        @Volatile
        private var INSTANCE: ItineraryDatabase? = null

        fun getDatabase(context: Context): ItineraryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItineraryDatabase::class.java,
                    "itinerary_database",
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}