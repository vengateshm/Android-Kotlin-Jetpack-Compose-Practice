package dev.vengateshm.android_kotlin_compose_practice.maps_spot_finder.data

import dev.vengateshm.android_kotlin_compose_practice.maps_spot_finder.domain.model.ParkingSpot
import dev.vengateshm.android_kotlin_compose_practice.maps_spot_finder.domain.repository.ParkingSpotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ParkingSpotRepositoryImpl(
    private val dao: ParkingSpotDao,
) : ParkingSpotRepository {
    override suspend fun insertParkingSpot(spot: ParkingSpot) {
        dao.insertParkingSpot(spot.toParkingSpotEntity())
    }

    override suspend fun deleteParkingSpot(spot: ParkingSpot) {
        dao.deleteParkingSpot(spot.toParkingSpotEntity())
    }

    override fun getParkingSpots(): Flow<List<ParkingSpot>> {
        return dao.getSparkingSpots().map { spots -> spots.map { it.toParkingSpot() } }
    }
}