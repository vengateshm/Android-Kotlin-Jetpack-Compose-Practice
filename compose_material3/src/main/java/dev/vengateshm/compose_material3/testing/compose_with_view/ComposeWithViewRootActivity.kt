package dev.vengateshm.compose_material3.testing.compose_with_view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class ComposeWithViewRootActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                var screen by remember { mutableStateOf("welcome") }
                when (screen) {
                    "welcome" -> WelcomeScreen(
                        onSignInClick = {
                            screen = it
                        }
                    )

                    else -> SignInScreen()
                }
            }
        }
    }
}