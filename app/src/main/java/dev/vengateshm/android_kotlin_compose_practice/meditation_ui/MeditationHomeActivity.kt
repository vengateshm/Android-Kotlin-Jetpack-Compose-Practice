package dev.vengateshm.android_kotlin_compose_practice.meditation_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.MeditationUITheme

class MeditationHomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MeditationUITheme {
                HomeScreen()
            }
        }
    }
}
