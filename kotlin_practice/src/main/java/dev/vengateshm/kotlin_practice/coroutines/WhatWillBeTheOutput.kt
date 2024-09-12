package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        launch { blockingFunction() }
        launch { nonBlockingFunction() }
//        launch { nonBlockingFunction1() }
    }
}

fun blockingFunction() {
    println("A :${Thread.currentThread().name}")
    Thread.sleep(1000)
    println("B :${Thread.currentThread().name}")
}

suspend fun nonBlockingFunction() {
    println("C :${Thread.currentThread().name}")
    delay(500)
    println("D :${Thread.currentThread().name}")
}

suspend fun nonBlockingFunction1() {
    println("E :${Thread.currentThread().name}")
    delay(500)
    println("F :${Thread.currentThread().name}")
}