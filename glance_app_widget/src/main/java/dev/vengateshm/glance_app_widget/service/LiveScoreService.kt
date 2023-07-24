package dev.vengateshm.glance_app_widget.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import dev.vengateshm.glance_app_widget.utils.startForegroundWithNotification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

const val LIVE_SCORE_FOREGROUND_SERVICE_ID = 112

class LiveScoreService : Service() {

    private val coroutineJob: Job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + coroutineJob)

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForegroundWithNotification(
            foregroundServiceId = LIVE_SCORE_FOREGROUND_SERVICE_ID,
            channelId = "LiveScoreService",
            channelName = "Live Score Channel",
            contentTitle = null,
            contentText = "Updating live cricket score...",
            null
        )
        coroutineScope.launch {
            try {
                updateLiveScoreWidget(this@LiveScoreService)
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