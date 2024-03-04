package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    val stateFlow = MutableStateFlow("")
    val flow = flowOf("Kotlin!")

    runBlocking {
        launch {
            collectStateFlow(stateFlow)
        }
        launch {
            collectFlow(flow)
        }
        delay(2000L)
        stateFlow.update { "New Value" }
    }
}

suspend fun collectStateFlow(stateFlow: StateFlow<String>) {
    stateFlow.collect {
        println("Collecting state flow")
    }
    println("After state flow")
}

suspend fun collectFlow(flow: Flow<String>) {
    flow.collect {
        println("Collecting flow")
    }
    println("After flow")
}