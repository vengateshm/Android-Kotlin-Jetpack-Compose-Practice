package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    runBlockingDelay(2000)
    myScope.coroutineContext.cancelChildren()
    myScope.launch {
        // This will print
        println("New coroutine launched after cancelling children")
    }
    runBlockingDelay(100)
}