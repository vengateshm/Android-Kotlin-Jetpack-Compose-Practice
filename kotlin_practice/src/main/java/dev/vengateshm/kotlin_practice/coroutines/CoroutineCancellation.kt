package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    runBlocking {
        val outerScopeJob = Job()
        val outerScope =
            CoroutineScope(outerScopeJob + CoroutineName("outer scope") + Dispatchers.IO)
        val job = outerScope.launch(CoroutineName("my coroutine") + Dispatchers.Default) {
            try {
                delay(100)
                withContext(CoroutineName("withContext") + Dispatchers.IO) {
                    try {
                        delay(100)
                        println("withContext done")
                    } catch (e: CancellationException) {
                        println("withContext cancelled")
                    }
                }
                println("my coroutine done")
            } catch (e: CancellationException) {
                println("my coroutine cancelled")
            }
        }
        outerScope.launch(CoroutineName("my other coroutine")) {
            delay(150)
//            outerScope.cancel()
            outerScopeJob.cancel()
            println("my other coroutine done")
        }

        job.join()
        println("main done")
    }
}