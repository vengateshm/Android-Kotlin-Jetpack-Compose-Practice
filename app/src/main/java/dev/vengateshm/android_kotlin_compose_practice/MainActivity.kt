package dev.vengateshm.android_kotlin_compose_practice

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import dev.vengateshm.android_kotlin_compose_practice.localization_app.LocaleContextWrapper
import dev.vengateshm.android_kotlin_compose_practice.shared_preferences_delegates.stringPrefs
import dev.vengateshm.android_kotlin_compose_practice.utils.requestPostNotificationsPermission

class MainActivity : ComponentActivity() {

    var token by stringPrefs("token")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPostNotificationsPermission()
        installSplashScreen()

        Firebase.messaging.subscribeToTopic("News")

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {

                }
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleContextWrapper(newBase!!))
    }
}
