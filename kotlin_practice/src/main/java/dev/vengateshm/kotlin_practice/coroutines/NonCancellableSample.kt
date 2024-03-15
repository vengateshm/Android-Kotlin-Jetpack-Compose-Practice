package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    val job = Job()
    val scope = CoroutineScope(Dispatchers.Default + job)

    scope.launch {
        try {
            repeat(10) { i ->
                println("Cancellable Job Working... ${i+1}")
                delay(1000)
            }
        } finally {
            println("Cancellable Job Completed or Cancelled")
        }
    }

    scope.launch {
        try {
            withContext(NonCancellable) {
                // Perform some potentially long-running operation here
                repeat(10) { i ->
                    println("NonCancellable Working... ${i+1}")
                    delay(1000)
                }
            }
        } finally {
            println("NonCancellable Job Completed or Cancelled")
        }
    }
    Thread.sleep(3000)
    job.cancel()
    Thread.sleep(20000)
}