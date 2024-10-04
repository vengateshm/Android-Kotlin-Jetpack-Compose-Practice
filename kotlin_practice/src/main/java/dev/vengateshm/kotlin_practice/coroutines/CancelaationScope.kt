package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val myScope = CoroutineScope(Dispatchers.Default)

fun main() {
    myScope.launch {
        println("Job 1 start")
        delay(2000L)
        println("Job 1 end")
    }
    myScope.launch {
        println("Job 2 start")
        delay(3000L)
        println("Job 2 end")
    }

    runBlocking {
        delay(2500)
    }
    myScope.coroutineContext.cancel()
}