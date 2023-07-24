package dev.vengateshm.android_kotlin_compose_practice.local_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.vengateshm.android_kotlin_compose_practice.local_notification.CounterNotificationService.Companion.COUNTER_NOT_CHANNEL_ID
import dev.vengateshm.android_kotlin_compose_practice.local_notification.CounterNotificationService.Companion.COUNTER_NOT_CHANNEL_NAME
import dev.vengateshm.android_kotlin_compose_practice.utils.requestPostNotificationsPermission

class LocalNotificationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPostNotificationsPermission()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            createNotificationChannel(
                context = this,
                channelId = COUNTER_NOT_CHANNEL_ID,
                channelName = COUNTER_NOT_CHANNEL_NAME,
                importance = NotificationManager.IMPORTANCE_HIGH
            )
        }

        val counterNotificationService = CounterNotificationService(this)

        setContent {
            MaterialTheme {
                val counterState = remember { Counter.counterState }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    Text(text = "Counter value ${counterState.value}")
                    Button(onClick = {
                        counterNotificationService.showNotification(Counter.counter)
                    }) {
                        Text(text = "Show Notification")
                    }
                }
            }
        }
    }

    fun createNotificationChannel(
        context: Context,
        channelId: String,
        channelName: String,
        importance: Int,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                importance
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}