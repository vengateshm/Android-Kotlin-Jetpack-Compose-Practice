package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val stateFlow = MutableStateFlow(0)
        val job1 = launch {
            stateFlow
                .onStart {
                    println("Start")
                }
                .onEmpty {
                    println("Empty")
                }
                .onEach {
                    println("Each $it")
                }
                .map {
                    it * it
                }
                .onEach {
                    println("After map $it")
                }
                .onCompletion {
                    println("Completed ${it?.message}")
                }
                .collect {
                    println("Collected $it")
                }

        }
        val job2 = launch {
            delay(3000L)
            stateFlow
                .onStart {
                    println("Start")
                }
                .onEmpty {
                    println("Empty")
                }
                .onEach {
                    println("Each $it")
                }
                .map {
                    it * it
                }
                .onEach {
                    println("After map $it")
                }
                .onCompletion {
                    println("Completed ${it?.message}")
                }
                .collect {
                    println("Collected $it")
                }

        }
        launch {
            repeat(10) {
                delay(1000L)
                stateFlow.value = it + 1
            }
        }

        delay(6000L)
        job1.cancel()
        job2.cancel()

        println("StateFlow value after cancellation ${stateFlow.value}")

        val job3 = launch {
            stateFlow.collect {
                println("New collector collected $it")
            }
        }
        delay(4000L)
        job3.cancel()
    }
}