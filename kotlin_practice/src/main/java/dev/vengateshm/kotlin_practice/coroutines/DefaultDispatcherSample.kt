package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun main() = runBlocking {
    val runtime = Runtime.getRuntime()
    val cores = runtime.availableProcessors()
    println("Cores: $cores")
    val jobs = List(3) {
        launch(Dispatchers.IO) { // Don't use Dispatchers.Default as this will block other coroutines
            println("Start blocking operation")
            TimeUnit.SECONDS.sleep(5)
            println("End blocking operation")
        }
    }

    launch(Dispatchers.Default) {
        println("This coroutine will be delayed")
    }
    jobs.forEach { it.join() }
    println("All coroutines finished")
}