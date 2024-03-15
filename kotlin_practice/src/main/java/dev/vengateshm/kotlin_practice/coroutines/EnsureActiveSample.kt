package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    runBlocking {
        val readFileJob = launch {
            readFile()
        }
        delay(1000)
        println("Main: Cancelling readFile job")
        readFileJob.cancel() // Cancel the job
    }
}

suspend fun readFile(): String {
    return withContext(Dispatchers.IO) {
        //ensureActive() - Wrong place to call as it called once
        // invoked readFileFunction
        val sb = StringBuilder()
        var counter = 1000000000
        while (counter != 0) {
            //ensureActive() // As while loop runs for some time, it keeps checking
            delay(100) // delay is also a cooperative function which checks isActive
            sb.append("counter-$counter")
            println(sb.toString())
            counter--
        }
        sb.toString()
    }
}