package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val myScope1 = CoroutineScope(Dispatchers.Default)

fun main() {
    myScope1.launch {
        println("Job 1 start")
        delay(2000L)
        println("Job 1 end")
    }
    myScope1.launch {
        println("Job 2 start")
        delay(3000L)
        println("Job 2 end")
    }

    runBlockingDelay(2000)
    myScope1.cancel()
    myScope1.launch {
        // This won't print
        println("Trying to launch new coroutine launched after cancelling scope")
    }
    runBlockingDelay(100)
}