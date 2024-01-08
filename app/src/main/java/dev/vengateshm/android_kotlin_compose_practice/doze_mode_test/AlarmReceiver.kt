package dev.vengateshm.android_kotlin_compose_practice.doze_mode_test

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import dev.vengateshm.android_kotlin_compose_practice.R

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)

                as NotificationManager
        createNotificationChannel(notificationManager)

        val notification = NotificationCompat.Builder(context, "alarm_channel")
            .setContentTitle("Alarm!")
            .setContentText("Alarm set.")
            .setSmallIcon(R.drawable.baseline_alarm_24)
            .build()

        notificationManager.notify(1, notification)
    }

    private fun createNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "alarm_channel",
                "Alarm Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}