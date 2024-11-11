package dev.vengateshm.kotlin_practice.class_extend_function

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

// Extend normal function
abstract class Mission : (String) -> Unit {
    abstract override fun invoke(description: String)
}

class Apollo11 : Mission() {
    override fun invoke(description: String) {
        println(description)
    }
}

// Extend suspend function
abstract class AsyncMission : suspend (String) -> Unit {
    abstract override suspend fun invoke(description: String)
}

class Artemis : AsyncMission() {
    private val lightYears = 3 * 1000L
    override suspend fun invoke(description: String) {
        delay(lightYears)
        println(description)
    }
}

fun main() {
    val apollo11 = Apollo11()
    apollo11("Mission : Apollo 11, Vehicle : Saturn V")
    val artemis = Artemis()
    runBlocking {
        artemis("Mission : Artemis, Vehicle : SpaceX")
    }
    MyFunctionClass()()
}

typealias MyFunction = () -> Unit

class MyFunctionClass : MyFunction {
    override fun invoke() {
        println("MyFunctionClass called")
    }
}