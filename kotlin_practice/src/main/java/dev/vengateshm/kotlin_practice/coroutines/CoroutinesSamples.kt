package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
//    measureCoroutineExecutionTime()
//    measureCoroutineExecutionTimeSynchronous()
//    catchExceptionOfLaunchCoroutineBuilder()
//    catchExceptionOfAsyncCoroutineBuilder()
//    fireAndForget()
//    getReturnValueFromCoroutine()
//    parallelExecutionUsingCoroutineScopeBuilder()
//    coroutineWithTimeout()
//    cancellingCoroutine()
//    executeCoroutinesInParallelWithResult()
}

fun executeCoroutinesInParallelWithResult() {
    val time = measureTimeMillis {
        runBlocking {
            val coffeePowder = async {
                println("Grinding coffee bean...")
                grindCoffeeBeans()
            }
            val hotMilk = async {
                println("Heating milk...")
                heatMilk()
            }
//        val ingredient1 = coffeePowder.await()
//        val ingredient2 = hotMilk.await()
            val (ingredient1, ingredient2) = awaitAll(coffeePowder, hotMilk)
            println("$ingredient1 + $ingredient2 = Hot Coffee!")
        }
    }
    println("Time ${time / 1000}s")
}

suspend fun grindCoffeeBeans(): String {
    delay(1000)
    return "Coffee powder"
}

suspend fun heatMilk(): String {
    delay(1000)
    return "Hot Milk"
}

fun cancellingCoroutine() {
    runBlocking {
        var i = 0
        var j = 0
        val scope = CoroutineScope(Dispatchers.Default)
        val job1 = scope.launch {
            while (true) {
                delay(Random.nextLong(1, 10) * 10)
                i += 1
            }
        }
        val job2 = scope.launch {
            while (true) {
                delay(Random.nextLong(1, 10) * 10)
                j += 1
            }
        }
        while (true) {
            print("Enter command (current, cancel or exit): ")
            when (readLine()) {
                "current" -> {
                    println("Current value of job1 is $i")
                    println("Current value of job2 is $j")
                }

                "cancel" -> {
                    job1.cancel()
                    println("Final value of job1 was $i")
                    println("Final value of job2 was $j")
                }

                "exit" -> {
                    println("Thank you, come again!")
                    break
                }

                else -> {
                    println("Command must be current, cancel or exit")
                }
            }
        }
    }
}

fun coroutineWithTimeout() {
    runBlocking {
        try {
            withTimeout(5000) {
                var i = 0
                while (true) {
                    delay(1000)
                    i++
                    println(i)
                }
            }
        } catch (e: Exception) {
            println("Coroutine cancelled on time out!")
        }
    }
}

fun parallelExecutionUsingCoroutineScopeBuilder() {
    val time = measureTimeMillis {
        runBlocking {
            coroutineScope {
                // If one child coroutine fails then
                // rest of the child coroutines will be cancelled
                launch {
                    delay(0)
                    println("C1")
                }
                launch {
                    delay(1000)
                    println("C2")
                    throw Exception("Exception")
                }
                launch {
                    delay(2000)
                    println("C3")
                }
            }
        }
    }
    println("${time / 1000}s")
}

fun getReturnValueFromCoroutine() {
    runBlocking {
        val deferred = async {
            getStringValue()
        }
        println(deferred.await().uppercase())
    }
}

suspend fun getStringValue(): String {
    delay(4000)
    return "I am String value!"
}

fun fireAndForget() {
    runBlocking {
        launch {
            delay(4000)
            println("Fire and forget!")
        }
    }
}

fun catchExceptionOfAsyncCoroutineBuilder() {
    runBlocking {
        val scope = CoroutineScope(Dispatchers.Default)
        val deferred = scope.async {
            try {
                delayAndThrowException()
            } catch (e: Exception) {
                println(e.message)
            }
        }
        deferred.join()
    }
}

fun catchExceptionOfLaunchCoroutineBuilder() {
    runBlocking {
        val coroutineScope = CoroutineScope(Dispatchers.Default)
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            println(throwable.message)
        }
        val job = coroutineScope.launch(exceptionHandler) {
            delayAndThrowException()
        }
        job.join()
    }
}

suspend fun delayAndThrowException() {
    delay(4000)
    throw Exception("Timed out!")
}

fun measureCoroutineExecutionTime() {
    // Measure two coroutines execution time
    val time = measureTimeMillis {
        runBlocking {
            launch {
                delay(5000)
                println("Coroutine 1")
            }
            launch {
                delay(10000)
                println("Coroutine 2")
            }
        }
    }
    println("${time / 1000}s")
}

fun measureCoroutineExecutionTimeSynchronous() {
    // Measure two coroutines execution time
    val scope = CoroutineScope(Dispatchers.Default)
    scope.launch {
        val time = measureTimeMillis {
            delay1()
            delay2()
        }
        println("${time / 1000}s")
    }
    readLine()
}

suspend fun delay1() {
    delay(10000)
    println("Delay 1")
}

suspend fun delay2() {
    delay(10000)
    println("Delay 2")
}