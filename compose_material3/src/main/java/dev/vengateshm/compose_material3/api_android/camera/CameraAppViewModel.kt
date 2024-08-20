package dev.vengateshm.compose_material3.api_android.camera

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.Recording
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.video.AudioConfig
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File
import java.util.concurrent.Executor

class CameraAppViewModel : ViewModel() {
    private val _images = MutableStateFlow<List<Bitmap>>(emptyList())
    val images = _images.asStateFlow()

    fun onTookPhoto(image: Bitmap) {
        _images.value += image
    }

    var recording: Recording? = null

    @SuppressLint("MissingPermission")
    fun recordVideo(
        controller: LifecycleCameraController,
        file: File,
        executor: Executor,
        onResult: (String) -> Unit
    ) {
        if (recording != null) {
            recording?.stop()
            recording = null
            return
        }

        recording = controller.startRecording(
            FileOutputOptions.Builder(file).build(),
            AudioConfig.create(true),
            executor
        ) { event ->
            when (event) {
                is VideoRecordEvent.Finalize -> {
                    if (event.hasError()) {
                        recording?.close()
                        recording = null
                        onResult("Video recording failed")
                    } else {
                        onResult("Video recording succeeded")
                    }
                }
            }
        }
    }
}