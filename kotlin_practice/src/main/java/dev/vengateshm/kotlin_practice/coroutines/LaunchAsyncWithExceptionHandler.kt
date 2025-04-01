package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job = Job()
        val dispatcher = Dispatchers.IO
        val exceptionHandler = CoroutineExceptionHandler { context, ex ->
            println(ex)
        }
        val context = dispatcher + job + exceptionHandler
        async(context = context) {
            println("Inside async")
            throw Exception("Exception in async")
        }
        launch(context = context) {
            println("Inside launch")
            throw Exception("Exception in launch")
        }
        println("End")
    }
}