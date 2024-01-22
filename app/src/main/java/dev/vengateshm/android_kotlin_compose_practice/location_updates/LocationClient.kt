package dev.vengateshm.android_kotlin_compose_practice.location_updates

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationClient {
    fun getLocationUpdates(interval: Long): Flow<Location>

    class LocationException(message: String) : RuntimeException()
}
