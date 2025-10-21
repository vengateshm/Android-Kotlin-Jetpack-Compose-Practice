package dev.vengateshm.compose_material3.api_android.work_manager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class UploadWorker(
  appContext: Context,
  workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams) {

  override suspend fun doWork(): Result {
    Log.d("UploadWorker", "Work started — WakeLock acquired automatically")

    try {
      for (i in 1..5) {
        delay(2000)
        Log.d("UploadWorker", "Uploading... Step $i/5")
      }

      Log.d("UploadWorker", "Work completed successfully")
      return Result.success()
    } catch (e: Exception) {
      Log.e("UploadWorker", "Work failed", e)
      return Result.retry()
    }
  }
}
