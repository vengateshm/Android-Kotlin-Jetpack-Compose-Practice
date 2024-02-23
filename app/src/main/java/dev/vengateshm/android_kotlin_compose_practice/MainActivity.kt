package dev.vengateshm.android_kotlin_compose_practice

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import dev.vengateshm.android_kotlin_compose_practice.animation_with_reading_large_file.AnimationWithFileRead
import dev.vengateshm.android_kotlin_compose_practice.animations.InfiniteColorTransition
import dev.vengateshm.android_kotlin_compose_practice.canvas.SVGToXYCoordinatesAnimation
import dev.vengateshm.android_kotlin_compose_practice.localization_app.LocaleContextWrapper
import dev.vengateshm.android_kotlin_compose_practice.media3_exoplayer.Media3ExoPlayer
import dev.vengateshm.android_kotlin_compose_practice.shared_preferences_delegates.stringPrefs
import dev.vengateshm.android_kotlin_compose_practice.system_bars_hide_show.SystemBarHideShow
import dev.vengateshm.android_kotlin_compose_practice.text_apis.AutoRefreshTimeTextSample
import dev.vengateshm.android_kotlin_compose_practice.text_apis.SelectableText
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
//                    EdgeToEdge()
//                    val context = LocalContext.current
//                    CompositionLocalProvider(LocalContext provides LocaleContextWrapper(context)) {
//                        LocalizationApp()
//                    }
//                    TermsAcknowledgeText(onPrivacyClicked = { /*TODO*/ }) {

//                    }
//                    CheeseFactory()
//                    FormComposable()
//                    OtpFormField()
//                    MultiHeaderLazyColumn()
//                    DraggableShape()
//                    AutoRefreshTimeTextSample()
//                    SystemBarHideShow()
//                    AnimationWithFileRead()
//                    Media3ExoPlayer()
//                    SelectableText()
//                    SVGToXYCoordinatesAnimation()
                }
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleContextWrapper(newBase!!))
    }
}
