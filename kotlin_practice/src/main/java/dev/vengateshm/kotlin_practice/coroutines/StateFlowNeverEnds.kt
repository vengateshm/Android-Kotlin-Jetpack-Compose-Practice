package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val stateFlow = MutableStateFlow(0)
        launch {
            stateFlow.collect {
                println("Collected $it")
            }
        }
        launch {
            repeat(5) {
                delay(1000L)
                stateFlow.value = it + 1
            }
        }
    }
}