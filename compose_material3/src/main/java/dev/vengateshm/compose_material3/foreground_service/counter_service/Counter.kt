package dev.vengateshm.compose_material3.foreground_service.counter_service

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Counter {

    private var countValue = 0
    private var isRunning = true

    fun start(): Flow<Int> = flow {
        while (isRunning) {
            emit(countValue)
            delay(1000)
            countValue++
        }
    }

    fun stop() {
        isRunning = false
        countValue = 0
    }
}