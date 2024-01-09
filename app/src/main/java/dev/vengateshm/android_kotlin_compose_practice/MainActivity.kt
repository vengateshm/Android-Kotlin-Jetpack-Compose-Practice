package dev.vengateshm.android_kotlin_compose_practice

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dev.vengateshm.android_kotlin_compose_practice.edge_to_edge.EdgeToEdge
import dev.vengateshm.android_kotlin_compose_practice.localization_app.LocaleContextWrapper
import dev.vengateshm.android_kotlin_compose_practice.localization_app.LocalizationApp
import dev.vengateshm.android_kotlin_compose_practice.message_queue_animation.MessageQueueAnimation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
//                    EdgeToEdge()
                    val context = LocalContext.current
                    CompositionLocalProvider(LocalContext provides LocaleContextWrapper(context)) {
                        LocalizationApp()
                    }
                }
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleContextWrapper(newBase!!))
    }
}