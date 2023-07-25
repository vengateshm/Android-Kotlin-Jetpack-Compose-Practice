package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

var counter = 0

//var counter = AtomicInteger(0)
val coroutines = 99
val updates = 999
val singleThreadContext = newSingleThreadContext("My")
val mutex = Mutex()

fun main() {
    runBlocking {
        withContext(Dispatchers.Default) {
//        withContext(singleThreadContext) {
            updateCounter()
        }
        println("Counter ${counter}")
    }
}

suspend fun updateCounter() {
    val timeInMillis = measureTimeMillis {
//        coroutineScope { // Will crash app as one of the children failed
        supervisorScope { // Will not stop if in same thread
            for (i: Int in 1..coroutines) {
                launch {
                    if (i == 3) {
                        //throw Exception("Failed")
                    }
                    println("Thread ${Thread.currentThread().name}")
                    for (j: Int in 1..updates) {
//                        counter++ // Same thread no race occurs
//                        counter.incrementAndGet()
                        mutex.withLock {
                            counter++
                        }
                    }
                }
            }
        }
    }
    println("TimeInMillis $timeInMillis")
}