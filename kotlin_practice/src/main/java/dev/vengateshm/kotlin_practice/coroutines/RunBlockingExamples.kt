package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    // Scenario 1
    /*val totalTime = measureTimeMillis {
        val delay1 = delayFunction1()
        val delay2 = delayFunction2()
    }
    println("Done!👍")
    println("Total time taken $totalTime ms")*/

    // Scenario 2
    /*val totalTime = measureTimeMillis {
        async { delayFunction1() }.await()
        async { delayFunction2() }.await()
    }
    println("Done!👍")
    println("Total time taken $totalTime ms")*/

    // Scenario 3
    val totalTime = measureTimeMillis {
        launch { delayFunction1() }.join()
        launch { delayFunction2() }.join()
    }
    println("Done!👍")
    println("Total time taken $totalTime ms")
}

suspend fun delayFunction1(): Long {
    val delayTime = 2000L
    delay(delayTime)
    println("delayFunction1")
    return delayTime
}

suspend fun delayFunction2(): Long {
    val delayTime = 1000L
    delay(delayTime)
    println("delayFunction2")
    return delayTime
}