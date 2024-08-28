package dev.vengateshm.glance_app_widget.utils

import android.app.*
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import dev.vengateshm.glance_app_widget.R

fun Service.startForegroundWithNotification(
    foregroundServiceId: Int,
    channelId: String,
    channelName: String,
    contentTitle: String?,
    contentText: String,
    @DrawableRes smallIcon: Int?
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        this.startForeground(
            foregroundServiceId,
            createNotification(
                context = this.applicationContext,
                channelId = channelId,
                channelName = channelName,
                contentTitle = contentTitle,
                contentText = contentText,
                smallIcon = smallIcon
            )
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun createNotification(
    context: Context,
    channelId: String,
    channelName: String,
    contentTitle: String?,
    contentText: String,
    @DrawableRes smallIcon: Int?,
    pendingIntent: PendingIntent? = null
): Notification {
    val notificationChannel =
        NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_NONE)
    notificationChannel.description = channelId
    notificationChannel.setSound(null, null)
    notificationChannel.lightColor = Color.BLUE
    notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(notificationChannel)

    return Notification.Builder(context, channelId).let { builder ->
        contentTitle?.let {
            builder.setContentTitle(contentTitle)
        }
        builder.setContentText(contentText)
        builder.setSmallIcon(smallIcon ?: R.drawable.ic_baseline_notifications_24)
        pendingIntent?.let { builder.setContentIntent(it) }
        builder.build()
    }
}
