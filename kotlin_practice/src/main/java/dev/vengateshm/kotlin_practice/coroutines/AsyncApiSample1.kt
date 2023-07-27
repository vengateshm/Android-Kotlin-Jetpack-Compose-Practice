package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

suspend fun main() {
    getty()
}

suspend fun getty() {
    coroutineScope {
        val resA = async { getAliens() }
        resA.await()
        val resR = async { getRockets() }
        resR.await()
    }
}