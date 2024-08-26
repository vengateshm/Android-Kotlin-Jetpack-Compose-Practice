package dev.vengateshm.compose_material3.api_android.notifications

import android.app.NotificationManager
import android.widget.RemoteViews
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import dev.vengateshm.compose_material3.R
import dev.vengateshm.compose_material3.api_android.foreground_service.counter.COUNTER_NOTIFICATION_CHANNEL_ID
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun NotificationsLiveActivitySample(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            val remoteViews =
                RemoteViews(context.packageName, R.layout.cmaterial3_live_activity_notification)
            val builder =
                NotificationCompat.Builder(context, COUNTER_NOTIFICATION_CHANNEL_ID).apply {
                    setContentTitle("Counter")
                    setCustomBigContentView(remoteViews)
                    setSmallIcon(R.drawable.cmaterial3_counter_widget_preview_image)
                    setStyle(NotificationCompat.BigTextStyle())
                }
            val notification = builder.build()
            val notificationManager = context.getSystemService<NotificationManager>()
            notificationManager?.notify(1, notification)

            scope.launch {
                delay(5000)
                remoteViews.setImageViewResource(R.id.ivStage, R.drawable.notification_complete)
                notificationManager?.notify(1, notification)
                delay(5000)
                notificationManager?.cancel(1)
            }
        }) {
            Text(text = "Show Notification")
        }
    }
}