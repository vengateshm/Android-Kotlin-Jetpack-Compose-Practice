package dev.vengateshm.compose_material3.lifecycle_events

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LifecycleResumeEffect

@Composable
fun LifeCycleEventsSample() {
    // Listen and un listen
    LifecycleResumeEffect(key1 = true) {
        println("Listen on resume")
        onPauseOrDispose {
            println("Un listen on pause")
        }
    }
}

