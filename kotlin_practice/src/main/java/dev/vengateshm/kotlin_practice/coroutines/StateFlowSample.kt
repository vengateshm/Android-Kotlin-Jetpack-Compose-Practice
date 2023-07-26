package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val stateFlow by lazy { MutableStateFlow(0) }

fun updateStateFlow(value: Int) {
    runBlocking {
        stateFlow.emit(value)
    }
}

fun stateFlowCollector1(): Job {
    return CoroutineScope(Dispatchers.IO).launch {
        stateFlow.collect {
            println("Collector 1 : $it")
            println()
        }
    }
}

fun stateFlowCollector2(): Job {
    return CoroutineScope(Dispatchers.IO).launch {
        delay(5000L)
        stateFlow.collect {
            println("Collector 2 : $it")
            println()
        }
    }
}

fun main() {
    val job1 = stateFlowCollector1()
    val job2 = stateFlowCollector2()

    repeat(10) {
        updateStateFlow(it + 1)
        Thread.sleep(1000L)
    }

    job1.cancel()
    job2.cancel()
}
