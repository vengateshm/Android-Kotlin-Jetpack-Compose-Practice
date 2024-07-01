package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("Job: I'm working $i ...")
                    delay(500L)
                }
            } catch (ce: CancellationException) {
                println("Job: I'm cancelled")
            }
            println("Job: I'm finishing cleanup")
            delay(1000L) // Simulate long-running cleanup
            println("Job: Cleanup completed")
        }

        delay(1300L) // Let the job run for a while
        println("Main: I'm tired of waiting!")
//        job.cancel() // Cancel the job
        job.cancelAndJoin() // Cancel the job and wait for its completion
        println("Main: Now I can do something else")
    }
}