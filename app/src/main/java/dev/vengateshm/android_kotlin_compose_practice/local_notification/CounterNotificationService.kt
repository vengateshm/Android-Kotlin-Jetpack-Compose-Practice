package dev.vengateshm.android_kotlin_compose_practice.local_notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import dev.vengateshm.android_kotlin_compose_practice.R

class CounterNotificationService(
    private val context: Context,
) {
    fun showNotification(counter: Int) {
        val builder = NotificationCompat.Builder(context, COUNTER_NOT_CHANNEL_ID)
        builder.setContentTitle("Counter Notification")
        builder.setContentText("Counter value is $counter")
        builder.setSmallIcon(R.drawable.ic_circle_notification)

        val intent = Intent(context, LocalNotificationActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            1,
            intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )
        builder.setContentIntent(pendingIntent)

        // Create the pending intents for the increment and decrement actions.
        val incrementAction = PendingIntent.getBroadcast(
            context,
            1,
            Intent(context, CounterNotificationReceiver::class.java).apply {
                action = "increment"
            },
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        val decrementAction = PendingIntent.getBroadcast(
            context,
            1,
            Intent(context, CounterNotificationReceiver::class.java).apply {
                action = "decrement"
            },
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        builder.addAction(R.drawable.ic_add, "Increment", incrementAction)
        builder.addAction(R.drawable.ic_remove, "Decrement", decrementAction)

        // Create the notification.
        val notification = builder.build()

        // Get the notification manager.
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Notify the user.
        notificationManager.notify(1, notification)
    }

    companion object {
        val COUNTER_NOT_CHANNEL_ID = "counter_not_channel_id"
        val COUNTER_NOT_CHANNEL_NAME = "counter_not_channel_name"
    }
}