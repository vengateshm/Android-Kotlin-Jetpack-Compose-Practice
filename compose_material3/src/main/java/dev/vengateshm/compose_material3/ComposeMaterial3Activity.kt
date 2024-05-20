package dev.vengateshm.compose_material3

import android.app.DownloadManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.getSystemService
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.vengateshm.compose_material3.api_android.download_manager.DownloadManagerSample
import dev.vengateshm.compose_material3.api_android.download_manager.DownloadManagerViewModel
import dev.vengateshm.compose_material3.api_android.shared_preferences.EncryptedPreferenceManager
import dev.vengateshm.compose_material3.theme.Material3AppTheme
import dev.vengateshm.compose_material3.utils.requestNotificationPermission

@AndroidEntryPoint
class ComposeMaterial3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestNotificationPermission()
//        encryptedSharedPreferences()

        val downloadManager = getSystemService<DownloadManager>()

        setContent {
            Material3AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DownloadManagerSample(
                        viewModel = viewModel {
                            DownloadManagerViewModel(downloadManager = downloadManager!!)
                        }
                    )
                }
            }
        }
    }

    private fun encryptedSharedPreferences() {
        val prefs = EncryptedPreferenceManager.encryptedPreferences(this)
        prefs.edit().putString("Language", "fr").apply()
        Log.d("PREFS", prefs.getString("Language", "en") ?: "")
    }
}