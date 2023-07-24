package dev.vengateshm.glance_app_widget.workers

import android.content.Context
import android.os.Build
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import dev.vengateshm.glance_app_widget.service.LIVE_SCORE_FOREGROUND_SERVICE_ID
import dev.vengateshm.glance_app_widget.service.updateLiveScoreWidget
import dev.vengateshm.glance_app_widget.utils.createNotification

class LiveScoreWidgetCoroutineWorker(private val context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return super.getForegroundInfo()
    }

    override suspend fun doWork(): Result {
        return try {
            startForegroundService()
            updateLiveScoreWidget(context = context)
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }

    private suspend fun startForegroundService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setForeground(
                ForegroundInfo(
                    LIVE_SCORE_FOREGROUND_SERVICE_ID,
                    createNotification(
                        context = this.applicationContext,
                        channelId = "LiveScoreService",
                        channelName = "Live Score Channel",
                        contentTitle = null,
                        contentText = "Updating live cricket score...",
                        null
                    )
                )
            )
        }
    }
}