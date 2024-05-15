package dev.vengateshm.compose_material3.api_android.foreground_service.location_tracking

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import dev.vengateshm.compose_material3.R
import dev.vengateshm.compose_material3.api_android.foreground_service.counter.COUNTER_NOTIFICATION_CHANNEL_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

const val LOCATION_TRACKING_NOTIFICATION_CHANNEL_ID = "location_tracking_notification_channel_id"

enum class LocationTrackingAction {
    START,
    STOP
}

class LocationTrackingService : Service() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            LocationTrackingAction.START.name -> {
                start()
            }

            LocationTrackingAction.STOP.name -> {
                stop()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        val locationManager = LocationManager(applicationContext)

        val builder = NotificationCompat.Builder(this, COUNTER_NOTIFICATION_CHANNEL_ID).apply {
            setContentTitle("Location Tracker")
            setSmallIcon(R.drawable.baseline_my_location_24)
            setStyle(NotificationCompat.BigTextStyle())
        }
        val notification = builder.build()
        startForeground(1, notification)

        scope.launch {
            locationManager.trackLocation()
                .collect { location ->
                    println("Location : $location")
                    location.run {
                        val latitude = location.latitude.toString()
                        val longitude = location.longitude.toString()
                        getSystemService<NotificationManager>()?.notify(
                            1,
                            builder.setContentText("Location: $latitude, $longitude").build()
                        )
                    }
                }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun stop() {
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun getLocationTrackingNotificationChannel(): NotificationChannel {
            return NotificationChannel(
                LOCATION_TRACKING_NOTIFICATION_CHANNEL_ID,
                "Location Tracking",
                NotificationManager.IMPORTANCE_LOW,
            )
        }
    }
}