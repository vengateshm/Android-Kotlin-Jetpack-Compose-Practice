package dev.vengateshm.android_kotlin_compose_practice.compose_apis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.LocalLifecycleOwner

class ComposeApisActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val lifecycleOwner = LocalLifecycleOwner.current
            MaterialTheme {
                Surface(color = MaterialTheme.colors.background) {
//                    Screen()
//                    Parent()
//                    ProduceStateSample()
//                    ProduceStateSampleLive()
                    //DisposableStateScreen()
                    PerformOnLifeCycle(
                        lifecycleOwner = lifecycleOwner,
                        onStart = {
                            println("onStart Event")
                        },
                        onResume = {
                            println("onResume Event")
                        })
                }
            }
        }
    }
}