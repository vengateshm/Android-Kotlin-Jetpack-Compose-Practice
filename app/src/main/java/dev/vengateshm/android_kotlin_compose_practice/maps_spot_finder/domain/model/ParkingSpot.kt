package dev.vengateshm.android_kotlin_compose_practice.maps_spot_finder.domain.model

data class ParkingSpot(
    val lat: Double,
    val lng: Double,
    val id: Int? = null,
)
