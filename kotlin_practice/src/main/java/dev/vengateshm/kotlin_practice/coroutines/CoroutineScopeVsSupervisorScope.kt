package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlin.time.Duration.Companion.seconds

data class User(val id: Int, val name: String)
data class ChatRoom(val id: Int, val title: String)
data class AppConfig(val config: String)

suspend fun getUsers(): List<User> {
    delay(1.seconds)
    return listOf(User(1, "Alice"), User(2, "Ben"))
}

suspend fun getAppConfig(): AppConfig {
    delay(5.seconds)
    return AppConfig("Config")
}

suspend fun getChatRooms(): List<ChatRoom> {
    delay(1.5.seconds)
    throw IllegalStateException("Chat rooms not ready")
}

fun main() = runBlocking {
    coroutineScopeMethod()
//    supervisorScopeMethod()
}

private suspend fun coroutineScopeMethod() {
    try {
        coroutineScope {
            val users = async { getUsers() }
            val rooms = async { getChatRooms() }
            val config = async { getAppConfig() }

            println("Users : ${users.await()}")
            try {
                println("Chat rooms : ${rooms.await()}")
            } catch (e: Exception) {
                println(e.message)
            }
            //println("Chat rooms : ${rooms.await()}")
            println("Config : ${config.await()}")
        }
    } catch (e: Exception) {
        println("Inside parent catch block")
        //println(e.message)
    }
}

private suspend fun supervisorScopeMethod() {
    supervisorScope {
        val users = async { getUsers() }
        val rooms = async { getChatRooms() }
        val config = async { getAppConfig() }

        println("Users : ${users.await()}")
        try {
            println("Chat rooms : ${rooms.await()}")
        } catch (e: Exception) {
            println(e.message)
        }
        println("Config : ${config.await()}")
    }
}

