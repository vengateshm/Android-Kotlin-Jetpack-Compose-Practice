package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import kotlin.math.pow
import kotlin.random.Random

fun main() {
    runBlocking {
        val inputList = mutableListOf<Float>()
        for (i in 1..1000) {
            inputList.add(Random.nextFloat())
        }
        convertData(inputList)
            .collect {
                println("Progress $it")
            }

        val f1: Flow<String> = flow {
            emit("A")
            kotlinx.coroutines.delay(1000)
            emit("B")
            kotlinx.coroutines.delay(1000)
            emit("C")
            kotlinx.coroutines.delay(1000)
        }
        val f2: Flow<Int> = flow {
            emit(1)
            kotlinx.coroutines.delay(2000)
            emit(2)
            kotlinx.coroutines.delay(2000)
            emit(3)
            kotlinx.coroutines.delay(2000)
        }

        // Zip
        f1.zip(f2) { v1, v2 ->
            "$v2 $v1"
        }.collect {
            println(it)
        }

        // Combine
        f1.combine(f2) { v1, v2 ->
            "$v2 $v1"
        }.collect {
            println(it)
        }

        // Merge
        merge(f1, f2).collect {
            println(it)
        }

        retrySampleFlow()
            .retryWhen { cause: Throwable, attempt: Long ->
                if (cause is Exception && attempt < 3) {
                    println(attempt)
                    return@retryWhen true
                }
                false
            }
            .catch {
                println(it)
            }
            .collect {
                println(it)
            }
    }
}

fun convertData(data: List<Float>): Flow<Int> {
    val onePercentOfDataList = (data.size / 100) * 1
    return processData(data)
        .filter {
            //println("$it % $onePercentOfDataList = ${it % onePercentOfDataList}")
            it % onePercentOfDataList == 0
        }.map {
            //println(it)
            it / onePercentOfDataList
        }
        .flowOn(Dispatchers.IO)
}

fun processData(data: List<Float>) = flow {
    var processed = 0
    data.forEach {
        for (i in 1..1000) {
            it.pow(2.0f)
        }
        emit(processed++)
    }
}

fun retrySampleFlow() = flow<Int> {
    println("Called")
    10 / 0
    emit(1)
    emit(2)
    emit(3)
}