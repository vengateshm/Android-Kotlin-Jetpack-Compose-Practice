package dev.vengateshm.compose_material3.ui_concepts.paging.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BreweryEntity::class],
    version = 1
)
abstract class BreweryDatabase : RoomDatabase() {
    abstract fun breweryDao(): BreweryDao
}