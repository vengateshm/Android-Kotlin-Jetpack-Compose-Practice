package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job = launch(start = CoroutineStart.LAZY) {
            println("LAZY Start")
            delay(500L)
            println("LAZY End")
        }
        job.start()

        val job1 = launch(start = CoroutineStart.ATOMIC) {
            println("ATOMIC Start")
            delay(500L)
            println("ATOMIC End")
        }
        job1.cancel()

        val job3 = launch(start = CoroutineStart.UNDISPATCHED, context = Dispatchers.IO) {
            println("Start ${Thread.currentThread().name}")
            delay(500L)
            println("End ${Thread.currentThread().name}")
        }
    }
}