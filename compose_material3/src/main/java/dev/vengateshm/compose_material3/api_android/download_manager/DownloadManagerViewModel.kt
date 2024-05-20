package dev.vengateshm.compose_material3.api_android.download_manager

import android.app.DownloadManager
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

class DownloadManagerViewModel(private val downloadManager: DownloadManager) : ViewModel() {

    private var _imageDataList: MutableLiveData<List<ImageData>> =
        MutableLiveData(ImageData.getList())
    val imageDataList: LiveData<List<ImageData>> = _imageDataList

    fun downloadImage(imageData: ImageData) {
        val request = DownloadManager.Request(Uri.parse(imageData.url))
            .setTitle("Downloading image")
            .setDescription("Downloading image ${imageData.title}")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "image-${imageData.id}-${imageData.title}.jpg"
            )
        val downloadId = downloadManager.enqueue(request)
        monitorDownloadProgress(downloadId, imageData)
    }

    private fun monitorDownloadProgress(downloadId: Long, imageData: ImageData) {
        val query = DownloadManager.Query().setFilterById(downloadId)
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                downloadManager.query(query)?.use { cursor ->
                    if (cursor.moveToFirst()) {
                        val statusColumnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                        val bytesDownloadedColumnIndex =
                            cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)
                        val totalBytesColumnIndex =
                            cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES)
                        if (statusColumnIndex != -1 && bytesDownloadedColumnIndex != -1 && totalBytesColumnIndex != -1) {
                            val status = cursor.getInt(statusColumnIndex)
                            val bytesDownloaded = cursor.getInt(bytesDownloadedColumnIndex)
                            val totalBytes = cursor.getInt(totalBytesColumnIndex)

                            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                                timer.cancel()
                                updateImageData(
                                    imageData.copy(
                                        isDownloadInProgress = false,
                                        progress = 0f,
                                        remaining = "",
                                        total = ""
                                    )
                                )
                            } else {
                                val progress =
                                    if (totalBytes > 0) (bytesDownloaded * 100f / totalBytes) else 0f
                                println("VM progress $progress")
                                updateImageData(
                                    imageData.copy(
                                        isDownloadInProgress = status == DownloadManager.STATUS_RUNNING,
                                        progress = progress,
                                        remaining = bytesDownloaded.toLong().formatBytes(),
                                        total = totalBytes.toLong().formatBytes()
                                    )
                                )
                            }
                        } else {
                            timer.cancel()
                        }
                    }
                }
            }
        }, 0, 1000)
    }

    private fun updateImageData(imageData: ImageData) {
        _imageDataList.postValue(_imageDataList.value?.map {
            if (it.id == imageData.id) imageData else it
        })
    }
}

fun Long.formatBytes(): String {
    val kilobytes = 1024.0
    val megabytes = kilobytes * 1024
    val gigabytes = megabytes * 1024

    return when {
        this >= gigabytes -> String.format("%.2f GB", this / gigabytes)
        this >= megabytes -> String.format("%.2f MB", this / megabytes)
        this >= kilobytes -> String.format("%.2f KB", this / kilobytes)
        else -> String.format("%d Bytes", this)
    }
}
