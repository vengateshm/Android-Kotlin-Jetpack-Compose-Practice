package dev.vengateshm.android_kotlin_compose_practice.maps_spot_finder.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ParkingSpotDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParkingSpot(spot: ParkingSpotEntity)

    @Delete
    suspend fun deleteParkingSpot(spot: ParkingSpotEntity)

    @Query("SELECT * FROM parking_spot")
    fun getSparkingSpots(): Flow<List<ParkingSpotEntity>>
}