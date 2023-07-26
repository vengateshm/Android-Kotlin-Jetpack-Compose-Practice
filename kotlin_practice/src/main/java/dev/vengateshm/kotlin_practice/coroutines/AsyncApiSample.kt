package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val resR = async { getRockets() }
        val resA = async { getAliens() }
        /*val result = listOf(resR, resA).awaitAll()
        println(result)*/

        /*println(resA.await())
        println(resR.await())*/

        val e = async { getException() }
        val result = listOf(resR, resA, e).awaitAll()
        println(result)
    }
}

suspend fun getRockets(): String {
    println("Request Rockets. . .")
    delay(1000L)
    println("Got Rockets. . .")
    return "Rockets"
}

suspend fun getAliens(): String {
    println("Request Aliens. . .")
    delay(5000L)
    println("Got Aliens. . .")
    return "Aliens"
}

suspend fun getException(): String {
    delay(6000L)
    throw Exception()
}