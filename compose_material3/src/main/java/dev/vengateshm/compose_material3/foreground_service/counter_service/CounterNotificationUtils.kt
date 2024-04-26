package dev.vengateshm.compose_material3.foreground_service.counter_service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi

const val COUNTER_NOTIFICATION_CHANNEL_ID = "counter_notification_channel_id"

@RequiresApi(Build.VERSION_CODES.O)
object CounterNotificationUtils {

    fun getCounterNotificationChannel(): NotificationChannel {
        return NotificationChannel(
            COUNTER_NOTIFICATION_CHANNEL_ID,
            "Counter channel",
            NotificationManager.IMPORTANCE_LOW,
        )
    }
}