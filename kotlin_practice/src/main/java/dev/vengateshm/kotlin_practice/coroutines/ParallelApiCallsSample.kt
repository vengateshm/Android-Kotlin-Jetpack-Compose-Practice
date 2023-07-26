package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import java.util.concurrent.atomic.AtomicInteger

val apiHitCount = AtomicInteger(0)

suspend fun main() {
//    try {
//        coroutineScopeBehaviour()
//    } catch (e: Exception) {
//        println(e.message)
//    }
    supervisorScopeBehaviour()
    println("Api hit count ${apiHitCount.get()}")
}

suspend fun coroutineScopeBehaviour() {
    coroutineScope {
        println("Scope thread ${Thread.currentThread().name}")
        launch {
            println("API 1 before suspension ${Thread.currentThread().name}")
            delay(1000L)
            apiHitCount.incrementAndGet()
            println("API 1 after suspension ${Thread.currentThread().name}")
        }
        launch {
            println("API 2 before suspension ${Thread.currentThread().name}")
            delay(2000L)
            println("API 2 after suspension ${Thread.currentThread().name}")
            val result = 1 / 0
            apiHitCount.incrementAndGet()
        }
        launch {
            println("API 3 before suspension ${Thread.currentThread().name}")
            delay(3000L)
            apiHitCount.incrementAndGet()
            println("API 3 after suspension ${Thread.currentThread().name}")
        }
    }
}

suspend fun supervisorScopeBehaviour() {
    supervisorScope {
        println("Scope thread ${Thread.currentThread().name}")
        launch {
            println("API 1 before suspension ${Thread.currentThread().name}")
            delay(1000L)
            apiHitCount.incrementAndGet()
            println("API 1 after suspension ${Thread.currentThread().name}")
        }
        launch {
            println("API 2 before suspension ${Thread.currentThread().name}")
            delay(2000L)
            println("API 2 after suspension ${Thread.currentThread().name}")
            val result = 1 / 0
            apiHitCount.incrementAndGet()
        }
        launch {
            println("API 3 before suspension ${Thread.currentThread().name}")
            delay(3000L)
            apiHitCount.incrementAndGet()
            println("API 3 after suspension ${Thread.currentThread().name}")
        }
    }
}