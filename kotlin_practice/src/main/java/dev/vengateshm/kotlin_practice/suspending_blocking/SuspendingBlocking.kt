package dev.vengateshm.kotlin_practice.suspending_blocking

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

fun main() {
    val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    testSuspending(dispatcher)
//    testBlocking(dispatcher)
}

fun testSuspending(dispatcher: CoroutineDispatcher) {
    GlobalScope.launch(dispatcher) {
        println("Suspending task 1 thread ${Thread.currentThread().name}")
        println("Suspending task 1 start")
        timeTakingTask()
        println("Suspending task 1 end")
    }
    GlobalScope.launch(dispatcher) {
        println("Suspending task 2 thread ${Thread.currentThread().name}")
        println("Suspending task 2 start")
        timeTakingTask()
        println("Suspending task 2 end")
    }
}

fun testBlocking(dispatcher: CoroutineDispatcher) {
    GlobalScope.launch(dispatcher) {
        runBlocking {
            println("Blocking task 1 thread ${Thread.currentThread().name}")
            println("Blocking task 1 start")
            timeTakingTask()
            println("Blocking task 1 end")
        }
    }
    GlobalScope.launch(dispatcher) {
        runBlocking {
            println("Blocking task 2 thread ${Thread.currentThread().name}")
            println("Blocking task 2 start")
            timeTakingTask()
            println("Blocking task 2 end")
        }
    }
}

private suspend fun timeTakingTask() {
    withContext(Dispatchers.IO) {
        //Thread.sleep(5000L)
        delay(5000L)
    }
}
