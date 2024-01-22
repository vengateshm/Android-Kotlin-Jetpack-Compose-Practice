package dev.vengateshm.android_kotlin_compose_practice.maps_spot_finder.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ParkingSpotEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class ParkingSpotDatabase : RoomDatabase() {
    abstract val parkingSpotDao: ParkingSpotDao
}
