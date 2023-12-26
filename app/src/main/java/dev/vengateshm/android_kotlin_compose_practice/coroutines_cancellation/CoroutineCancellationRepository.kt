package dev.vengateshm.android_kotlin_compose_practice.coroutines_cancellation

import arrow.core.Either
import kotlinx.coroutines.delay
import kotlin.coroutines.cancellation.CancellationException

class CoroutineCancellationRepository {
    suspend fun getDataNotThrown() {
        try {
            delay(5000)
        } catch (e: Exception) {
            println(e.localizedMessage)
        }
    }

    suspend fun getData() {
        try {
            delay(5000)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            println(e.localizedMessage)
        }
    }

    suspend fun getDataEither(): Either<Throwable, Int> {
        return Either.catch {
            delay(5000)
            1000
        }.mapLeft {
            it
        }
    }
}