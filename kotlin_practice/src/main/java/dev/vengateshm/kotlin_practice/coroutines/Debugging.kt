package dev.vengateshm.kotlin_practice.coroutines

import dev.reformator.stacktracedecoroutinator.runtime.DecoroutinatorRuntime
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    /*val pool = newFixedThreadPoolContext(3, "myPool")
    runBlocking(pool + CoroutineName("main")) {
        repeat(6) {
            launch { describe("Kotlin") }
        }
    }*/

    runBlocking {
        DecoroutinatorRuntime.load()
        fun3()
    }
}

suspend fun describe(identifier: String): String {
    val description = fetchDescription(identifier)
    val enhancedDescription = enhanceDescription(description)
    return enhancedDescription
}

suspend fun fetchDescription(identifier: String): String {
    delay(100)
    return identifier
}

suspend fun enhanceDescription(description: String): String {
    delay(100)
    return description.uppercase()
}

suspend fun fun1() /*= coroutineScope<Unit>*/ {
    delay(100)
    throw Exception("Exception in fun1")
}

suspend fun fun2() {
    fun1()
    delay(100)
}

suspend fun fun3() {
    fun2()
    delay(100)
}