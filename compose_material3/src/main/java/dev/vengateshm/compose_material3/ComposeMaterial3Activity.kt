package dev.vengateshm.compose_material3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import dev.vengateshm.compose_material3.theme.Material3AppTheme
import dev.vengateshm.compose_material3.utils.requestCameraPermission
import dev.vengateshm.compose_material3.utils.requestNotificationPermission

@AndroidEntryPoint
class ComposeMaterial3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestNotificationPermission()
        requestCameraPermission()

        setContent {
            Material3AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}