package dev.vengateshm.android_kotlin_compose_practice.hilt_dependency_scopes

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SessionTimer {
    // If it required in two screens then scope it only in that
    // two screens instead scoping to the application lifecycle
    private var isRunning = false
    var sessionTime = 0 // In singleton if not reset then it will start from the value it left

    fun startTimer() {
        CoroutineScope(Dispatchers.Main).launch {
            while (isRunning) {
                delay(1000L)
                sessionTime++
            }
        }
    }

    fun stopTimer() {
        isRunning = false
    }
}