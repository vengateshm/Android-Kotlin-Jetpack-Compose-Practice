package dev.vengateshm.compose_material3

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import dagger.hilt.android.AndroidEntryPoint
import dev.vengateshm.compose_material3.theme.Material3AppTheme
import dev.vengateshm.compose_material3.utils.requestCameraPermission
import dev.vengateshm.compose_material3.utils.requestNotificationPermission
import dev.vengateshm.compose_material3.utils.requestRecordAudioPermission

@AndroidEntryPoint
class ComposeMaterial3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        windowBehaviour()
        requestPermissions()
        setContent {
            Material3AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {

                }
            }
        }
    }

    private fun requestPermissions() {
        requestNotificationPermission()
        requestCameraPermission()
        requestRecordAudioPermission()
    }

    private fun Activity.windowBehaviour() {
        window.statusBarColor = Color.RED
        window.navigationBarColor = Color.BLUE
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//        controller.hide(WindowInsetsCompat.Type.statusBars())
//        controller.hide(WindowInsetsCompat.Type.navigationBars())
    }
}