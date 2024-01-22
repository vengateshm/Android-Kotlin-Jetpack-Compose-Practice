package dev.vengateshm.android_kotlin_compose_practice.stopwatch

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.AndroidEntryPoint
import dev.vengateshm.android_kotlin_compose_practice.stopwatch.service.StopWatchService
import dev.vengateshm.android_kotlin_compose_practice.ui.theme.AndroidKotlinComposePracticeTheme

@AndroidEntryPoint
class StopWatchActivity : ComponentActivity() {
    private var isBound by mutableStateOf(false)
    private lateinit var stopWatchService: StopWatchService

    private val connection =
        object : ServiceConnection {
            override fun onServiceConnected(
                className: ComponentName?,
                binder: IBinder?,
            ) {
                val ser = binder as StopWatchService.StopWatchBinder
                stopWatchService = binder.getService()
                isBound = true
            }

            override fun onServiceDisconnected(p0: ComponentName?) {
                isBound = false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidKotlinComposePracticeTheme {
                if (isBound) {
                    MainScreen(stopWatchService = stopWatchService)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, StopWatchService::class.java).also {
            bindService(it, connection, BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        isBound = false
    }
}
