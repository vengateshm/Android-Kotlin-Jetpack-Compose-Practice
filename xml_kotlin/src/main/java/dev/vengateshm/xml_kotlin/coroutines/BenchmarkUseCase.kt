package dev.vengateshm.xml_kotlin.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class BenchmarkUseCase(private val postBenchmarkResultEndpoint: PostBenchmarkResultEndpoint = PostBenchmarkResultEndpoint()) {
    suspend fun executeBenchmark(benchmarkDurationInSeconds: Int): Long {
        logThreadInfo("benchmark started")
        var iterations = 0L
        val stopTimeNano = System.nanoTime() + benchmarkDurationInSeconds * 1_000_000_000L
        while (System.nanoTime() < stopTimeNano && coroutineContext.isActive) {
            iterations++
        }

        /*while (System.nanoTime() < stopTimeNano) {
            if (!coroutineContext.isActive) {
                throw CancellationException()
            }
            iterations++
        }*/

        /*while (System.nanoTime() < stopTimeNano) {
            coroutineContext.ensureActive()
            iterations++
        }*/

        logThreadInfo("benchmark completed")
        return iterations
    }

    suspend fun executeBenchmarkWithContext(benchmarkDurationInSeconds: Int) =
        withContext(Dispatchers.Default) {
            logThreadInfo("benchmark started")
            var iterations = 0L
            val stopTimeNano = System.nanoTime() + benchmarkDurationInSeconds * 1_000_000_000L
            while (System.nanoTime() < stopTimeNano) {
                ensureActive()
                iterations++
            }
            logThreadInfo("benchmark completed")

            postResults(benchmarkDurationInSeconds, iterations)

            iterations
        }

    private fun postResults(benchmarkDurationInSeconds: Int, iterations: Long) {
        postBenchmarkResultEndpoint.postBenchmarkResultEndpoint(
            benchmarkDurationInSeconds,
            iterations,
        )
        logThreadInfo("benchmark result posted to the server")
    }
}