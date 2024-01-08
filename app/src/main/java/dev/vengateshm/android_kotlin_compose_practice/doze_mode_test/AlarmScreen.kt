package dev.vengateshm.android_kotlin_compose_practice.doze_mode_test

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun AlarmScreen() {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { setAlarm(context) },
        ) {
            Text(text = "Set alarm")
        }
    }
}

fun setAlarm(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        1,
        intent,
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT else 0
    )

    val triggerAtMillis = System.currentTimeMillis() + 10000 // 10 seconds

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && alarmManager.canScheduleExactAlarms()) {
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent
        )
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent
        )
    } else {

    }
}