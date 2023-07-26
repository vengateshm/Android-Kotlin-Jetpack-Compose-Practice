package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val sharedFlow by lazy {
    MutableSharedFlow<Int>(
        replay = 2
    )
}

fun updateSharedFlow(value: Int) {
    runBlocking {
        sharedFlow.emit(value)
    }
}

fun sharedFlowCollector1(): Job {
    return CoroutineScope(Dispatchers.IO).launch {
        sharedFlow.collect {
            println("Collector 1 : $it")
            println()
        }
    }
}

fun sharedFlowCollector2(): Job {
    return CoroutineScope(Dispatchers.IO).launch {
        delay(5000L)
        sharedFlow.collect {
            println("Collector 2 : $it")
            println()
        }
    }
}

fun main() {
    val job1 = sharedFlowCollector1()
    val job2 = sharedFlowCollector2()

    repeat(10) {
        updateSharedFlow(it + 1)
        //updateSharedFlow(90)
        Thread.sleep(1000L)
    }

    job1.cancel()
    job2.cancel()
}