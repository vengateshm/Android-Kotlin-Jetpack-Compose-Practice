package dev.vengateshm.kotlin_practice.interview_evaluation

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        getSampleFlow()
            .distinctUntilChanged()
            .collect {
                print("$it ")
            }
        println()
        val time = measureTimeMillis {
            val result = add()
            println(result)
        }
        println("Time taken: $time ms")
    }
}

fun getSampleFlow() = flowOf(1, 1, 1, 2, 2, 3, 3, 1, 1, 4, 2, 2)

suspend fun add() = coroutineScope {
    val result1 = async {
        delay(1000)
        10
    }
    val result2 = async {
        delay(2000)
        20
    }
    result1.await() + result2.await()
}