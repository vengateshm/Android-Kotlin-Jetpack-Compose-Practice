package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val sharedFlow = MutableSharedFlow<Int>(replay = 1)
    val job1 = launch {
        sharedFlow.collect {
            //println("Collector 1: $it")
        }
    }
    val job2 = launch {
        delay(5000L)
        sharedFlow.collect {
            println("Collector 2: $it")
        }
    }
    launch {
        repeat(10) {
            delay(1000L)
            println("Emitting $it")
            sharedFlow.emit(it + 1)
        }
    }
    delay(7000L)
    job1.cancel()
    job2.cancel()
    println("SharedFlow value after cancellation ${sharedFlow.replayCache}")
    val job3 = launch {
        sharedFlow.collect {
            println("Collector 3: $it")
        }
    }
    delay(5000L)
    job3.cancel()
}