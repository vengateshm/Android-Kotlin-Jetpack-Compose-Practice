package dev.vengateshm.android_kotlin_compose_practice.manual_di

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.vengateshm.android_kotlin_compose_practice.AndroidKotlinComposePracticeApp

class ManualDiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    val viewModel = viewModel<SpamViewModel>(
                        factory = viewModelFactory {
                            SpamViewModel(AndroidKotlinComposePracticeApp.callAppModule.spamRepo)
                        }
                    )
                    SpamScreen(viewModel)
                }
            }
        }
    }
}