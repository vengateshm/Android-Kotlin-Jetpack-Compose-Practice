package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking

val coldFlow = flow<Int> {
    emit(1)
    delay(1500L)
    emit(1)
    delay(1500L)
    emit(2)
    delay(1500L)
    emit(3)
    delay(1500L)
}

val hotFlow = coldFlow
    .onStart {
        println("Started")
    }
    .stateIn(
        GlobalScope,
        SharingStarted.WhileSubscribed(5000L),
        0,
    )

fun main() {
    runBlocking {
        hotFlow.collect {
            println(it)
        }
    }
}