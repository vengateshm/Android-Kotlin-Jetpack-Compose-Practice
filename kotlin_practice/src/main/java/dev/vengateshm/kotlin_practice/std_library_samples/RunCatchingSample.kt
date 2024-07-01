package dev.vengateshm.kotlin_practice.std_library_samples

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val result = runCatching {
        completeSuccess(200)
    }
    println(result.getOrNull())
    println(result.exceptionOrNull())

    result
        .onSuccess {
            println(it)
        }
        .onFailure {
            println(it)
        }

    runBlocking {
        val job = launch {
            runSafeSuspendCatching {
                delay(5000L)
                "Hello"
            }
        }
        delay(1000)
        job.cancel()
    }
}

fun completeSuccess(code: Int): String {
    if (code != 200)
        throw RuntimeException("Error!")
    return "Success!"
}

inline suspend fun <R> runSafeSuspendCatching(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (ce: CancellationException) {
        println(ce)
        throw ce
    } catch (e: Throwable) {
        Result.failure(e)
    }
}