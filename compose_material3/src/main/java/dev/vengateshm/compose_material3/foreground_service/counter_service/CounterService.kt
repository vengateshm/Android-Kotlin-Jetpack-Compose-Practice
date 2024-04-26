package dev.vengateshm.compose_material3.foreground_service.counter_service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import dev.vengateshm.compose_material3.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CounterService : Service() {

    private var counter = Counter()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when (intent?.action) {
            CounterAction.START.name -> {
                start()
            }

            CounterAction.STOP.name -> {
                stop()
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        CoroutineScope(Dispatchers.Default).launch {
            counter.start().collect { counterValue ->
                Log.d("Counter", counterValue.toString())
                notification(counterValue)
            }
        }
    }

    private fun notification(counterValue: Int) {
        val builder = NotificationCompat.Builder(this, COUNTER_NOTIFICATION_CHANNEL_ID).apply {
            setContentTitle("Counter")
            setContentText(counterValue.toString())
            setSmallIcon(R.drawable.cmaterial3_counter_widget_preview_image)
            setStyle(NotificationCompat.BigTextStyle())
        }
        val notification = builder.build()
        startForeground(1, notification)
    }

    private fun stop() {
        counter.stop()
        stopSelf()
    }
}