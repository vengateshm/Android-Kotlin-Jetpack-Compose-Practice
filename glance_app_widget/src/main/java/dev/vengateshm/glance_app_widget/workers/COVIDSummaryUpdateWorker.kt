package dev.vengateshm.glance_app_widget.workers

import android.content.Context
import android.os.Build
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import dev.vengateshm.glance_app_widget.service.COVID_DATA_FOREGROUND_SERVICE_ID
import dev.vengateshm.glance_app_widget.service.updateCOVID19Widget
import dev.vengateshm.glance_app_widget.utils.createNotification

class COVIDSummaryUpdateWorker(private val context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun getForegroundInfo(): ForegroundInfo = super.getForegroundInfo()

    override suspend fun doWork(): Result = try {
        // startForegroundService()
        updateCOVID19Widget(context = context)
        Result.success()
    } catch (e: Exception) {
        e.printStackTrace()
        Result.failure()
    }

    private suspend fun startForegroundService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setForeground(
                ForegroundInfo(
                    COVID_DATA_FOREGROUND_SERVICE_ID,
                    createNotification(
                        context = this.applicationContext,
                        channelId = "COVID19Service",
                        channelName = "COVID19 Summary Channel",
                        contentTitle = null,
                        contentText = "Updating COVID19 global summary...",
                        null
                    )
                )
            )
        }
    }
}
