package dev.vengateshm.compose_material3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dev.vengateshm.compose_material3.api_android.shared_preferences.EncryptedPreferenceManager
import dev.vengateshm.compose_material3.theme.Material3AppTheme
import dev.vengateshm.compose_material3.utils.requestNotificationPermission

class ComposeMaterial3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestNotificationPermission()
        encryptedSharedPreferences()

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

    private fun encryptedSharedPreferences() {
        val prefs = EncryptedPreferenceManager.encryptedPreferences(this)
        prefs.edit().putString("Language", "fr").apply()
        Log.d("PREFS", prefs.getString("Language", "en") ?: "")
    }
}