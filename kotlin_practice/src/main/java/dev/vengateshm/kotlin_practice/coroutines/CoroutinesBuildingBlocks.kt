package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun CoroutineScope.printInfo() {
    println("CoroutineScope: $this")
    println("CoroutineContext: ${this.coroutineContext}")
    println("Job: ${this.coroutineContext[Job]}")
    println("")
    println("")
}

fun printJobsHierarchy(job: Job, nestLevel: Int = 0) {
    val indent = "    ".repeat(nestLevel)
    println("$indent- $job")
    for (childJob in job.children) {
        printJobsHierarchy(childJob, nestLevel + 1)
    }
}

fun main() {
    runBlocking {
        val scopeJob = Job()
        printJobsHierarchy(scopeJob)
        val scope = CoroutineScope(scopeJob + CoroutineName("outer scope") + Dispatchers.IO)
        scope.printInfo()
        val job = scope.launch(CoroutineName("coroutine") + Dispatchers.Default) {
            this.printInfo()
            delay(100)
            withContext(CoroutineName("withContext") + Dispatchers.IO) {
                this.printInfo()
                delay(100)
                printJobsHierarchy(scopeJob)
                println("withContext done")
            }
            println("coroutine done")
        }
        scope.launch {
            delay(500)
        }
        job.join()
        println("main done")
    }
}