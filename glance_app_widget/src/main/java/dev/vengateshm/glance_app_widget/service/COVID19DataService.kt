package dev.vengateshm.glance_app_widget.service

import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import android.preference.PreferenceManager
import com.google.gson.Gson
import dev.vengateshm.glance_app_widget.receiver.COVID19WidgetReceiver
import dev.vengateshm.glance_app_widget.utils.SUMMARY_RESPONSE_KEY
import dev.vengateshm.glance_app_widget.utils.startForegroundWithNotification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

const val COVID_DATA_FOREGROUND_SERVICE_ID = 111

class COVID19DataService : Service() {

    private val coroutineJob: Job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + coroutineJob)

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForegroundWithNotification(
            foregroundServiceId = COVID_DATA_FOREGROUND_SERVICE_ID,
            channelId = "COVID19Service",
            channelName = "COVID19 Summary Channel",
            contentTitle = null,
            contentText = "Updating COVID19 global summary...",
            null
        )

        coroutineScope.launch {
            try {
                updateCOVID19Widget(this@COVID19DataService)
                stopSelf()
            } catch (e: Exception) {
                stopSelf()
                e.printStackTrace()
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        coroutineJob.cancel()
    }
}