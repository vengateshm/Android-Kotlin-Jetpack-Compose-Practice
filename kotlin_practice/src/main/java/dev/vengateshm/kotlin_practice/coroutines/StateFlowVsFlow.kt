package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
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

/**
 * No, a StateFlow does not complete in the same way as a Flow.
 * In a Flow, you can have a complete signal, which means that the Flow has completed and will not emit any more items. This is typically represented by the onCompletion operator or a terminal operator like collect with a defined termination condition.
 * In contrast, a StateFlow is designed to represent a continuous and reactive state, such as the current state of a UI component. It does not have a concept of completion because it's meant to always reflect the current state and automatically update whenever the underlying value changes.
 * A StateFlow doesn't complete because it's expected to be observed continuously as long as the associated object or state exists. It will emit new values whenever the value it represents changes, making it suitable for observing changes in state over time.
 * So, in summary, while a Flow can complete and signal the end of emission, a StateFlow is designed to always be available for observation and reflect the current state.
 * */

class MainViewModel {
    val flow = flow { emit(1) }
    val stateFlow = MutableStateFlow(1)

    init {
        GlobalScope.launch {
            stateFlow.collectLatest {

            }
            println("StateFlow")
        }
        GlobalScope.launch {
            flow.collectLatest {

            }
            println("Flow")
        }
    }
}