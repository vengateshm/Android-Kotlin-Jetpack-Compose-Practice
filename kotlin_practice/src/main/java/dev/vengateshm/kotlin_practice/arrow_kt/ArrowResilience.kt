package dev.vengateshm.kotlin_practice.arrow_kt

import arrow.fx.coroutines.parMap
import arrow.resilience.Schedule
import arrow.resilience.retry
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

val retryPolicy = Schedule
    .exponential<Throwable>(250.milliseconds)
    .doUntil { _, duration ->
        duration > 15.seconds
    }

suspend fun main() {
    runBlocking {
        val result = fetchIds()
            .map { async { fetchById(it) } }
            .awaitAll()
        println(result)

        val result1 = fetchIds()
            .parMap { id -> fetchById(id) }
        println(result1)

        retryPolicy.retry {
            getData()
        }
    }
}

suspend fun fetchIds(): List<Int> {
    delay(100L)
    return listOf(1, 2, 3, 4, 5)
}

suspend fun fetchById(id: Int): String {
    delay(100L)
    return "$id"
}

suspend fun getData(): String {
    println("Calling getData()")
    delay(1000L)
    throw RuntimeException("Failed to get data")
}