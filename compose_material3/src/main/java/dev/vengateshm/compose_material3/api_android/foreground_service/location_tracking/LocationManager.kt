package dev.vengateshm.compose_material3.api_android.foreground_service.location_tracking

import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class LocationManager(private val context: Context) {

    private val fusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    fun getLocation(onSuccess: (latitude: String, longitude: String) -> Unit) {
        val locationRequest =
            LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY,
                100
            )
                .setIntervalMillis(1000)
                .setMaxUpdates(1)
                .build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.locations[0]?.let { location ->
                    val latitude = location.latitude.toString()
                    val longitude = location.longitude.toString()
                    onSuccess(latitude, longitude)
                } ?: onSuccess("0.0", "0.0")
            }
        }

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    fun trackLocation() = callbackFlow {
        val locationCallback = locationCallback {
            launch {
                send(it)
            }
        }
        val locationRequest =
            LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY,
                100
            )
                .setIntervalMillis(5000)
                .build()
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )

        awaitClose {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        }
    }

    private fun locationCallback(
        onResult: (Location) -> Unit
    ): LocationCallback {
        return object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                super.onLocationResult(result)
                result.locations.lastOrNull()?.let(onResult)
            }
        }
    }
}