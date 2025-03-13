package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val sharedFlow = MutableSharedFlow<Int>()
    launch {
        sharedFlow.collect {
            println("Collector 1: $it")
        }
    }
    launch {
        sharedFlow.collect {
            println("Collector 2: $it")
        }
    }
    launch {
        repeat(10) {
            delay(1000L)
            sharedFlow.emit(it + 1)
        }
    }
}