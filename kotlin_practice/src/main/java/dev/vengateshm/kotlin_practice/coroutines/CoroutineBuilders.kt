package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        launch {
            val result = getUserInfo("first", 4000)
            println(result)
        }
        val result = async { getUserInfo("second", 2000) }
//        println(result.await())
//        println(result.getCompletionExceptionOrNull())
        println(result.getCompleted())
    }
    println("end main")
}

suspend fun getUserInfo(userId: String, delay: Long): String {
    delay(delay)
    return userId
}