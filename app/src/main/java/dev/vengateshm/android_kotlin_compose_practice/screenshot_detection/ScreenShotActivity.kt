package dev.vengateshm.android_kotlin_compose_practice.screenshot_detection

import android.app.Activity.ScreenCaptureCallback
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class ScreenShotActivity : AppCompatActivity() {

    private lateinit var screenshotDetector: ScreenshotDetector

    private val screenCaptureCallback = ScreenCaptureCallback {
        println("Screenshot detected")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenshotDetector = ScreenshotDetector(baseContext)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Screenshot page!")
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        detectScreenshots()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            registerScreenCaptureCallback(mainExecutor, screenCaptureCallback)
        }
    }

    override fun onStop() {
        super.onStop()
        screenshotDetector.stop()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            unregisterScreenCaptureCallback(screenCaptureCallback)
        }
    }

    private fun detectScreenshots() {
        screenshotDetector.start()
    }
}