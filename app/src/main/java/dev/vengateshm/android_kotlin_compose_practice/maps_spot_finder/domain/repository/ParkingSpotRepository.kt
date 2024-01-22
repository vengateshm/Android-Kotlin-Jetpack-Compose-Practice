package dev.vengateshm.android_kotlin_compose_practice.maps_spot_finder.domain.repository

import dev.vengateshm.android_kotlin_compose_practice.maps_spot_finder.domain.model.ParkingSpot
import kotlinx.coroutines.flow.Flow

interface ParkingSpotRepository {
    suspend fun insertParkingSpot(spot: ParkingSpot)

    suspend fun deleteParkingSpot(spot: ParkingSpot)

    fun getParkingSpots(): Flow<List<ParkingSpot>>
}
