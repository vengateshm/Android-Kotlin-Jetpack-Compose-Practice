package dev.vengateshm.kotlin_practice.concurrency

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SynchronizedCounter {
    val lock = Any()
    var count = 0

    fun increment() {
        synchronized(lock) { // Can use AtomicInteger
            count++
        }
        println("Counter updated to: $count by ${Thread.currentThread().name}")
    }
}

fun main(): Unit = runBlocking {
    val counter = SynchronizedCounter()

    val jobs = List(50) {
        launch(Dispatchers.Default) {
            repeat(5) {
                counter.increment()
                delay(100L)
            }
        }
    }
    jobs.forEach { it.join() }
    println("Final Counter Value: Expected 250, Actual ${counter.count}")
}