package dev.vengateshm.android_kotlin_compose_practice.compose_apis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import dev.vengateshm.android_kotlin_compose_practice.compose_apis.remember_updated_state.Parent
import dev.vengateshm.android_kotlin_compose_practice.compose_apis.remember_updated_state.Screen

class ComposeApisActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colors.background) {
//                    Screen()
//                    Parent()
//                    ProduceStateSample()
//                    ProduceStateSampleLive()
                    DisposableStateScreen()
                }
            }
        }
    }
}