package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main(): Unit = runBlocking {
    try {
        coroutineScope {
            val dispatcher = Dispatchers.IO
            val exceptionHandler = CoroutineExceptionHandler { context, ex ->
                println(ex)
            }
            val context = dispatcher + exceptionHandler + this.coroutineContext.job
            launch(context = context) {
                println("Inside launch")
                throw Exception("Exception in launch")
            }
        }
    } catch (e: Exception) {
        println(e.message)
    }
    supervisorScope {
        val dispatcher = Dispatchers.IO
        val exceptionHandler = CoroutineExceptionHandler { context, ex ->
            println(ex)
        }
        val context = dispatcher + exceptionHandler
        launch(context = context) {
            println("Inside launch")
            throw Exception("Exception in launch")
        }
    }
}