package dev.vengateshm.android_kotlin_compose_practice.local_notification

import androidx.compose.runtime.mutableStateOf

object Counter {
    var counter = 0
        set(value) {
            field = value
            counterState.value = value
        }

    val counterState = mutableStateOf(counter)
}
