package dev.vengateshm.kotlin_practice.benchmark

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.BenchmarkMode
import kotlinx.benchmark.Blackhole
import kotlinx.benchmark.Measurement
import kotlinx.benchmark.Mode
import kotlinx.benchmark.OutputTimeUnit
import kotlinx.benchmark.Scope
import kotlinx.benchmark.Setup
import kotlinx.benchmark.State
import kotlinx.benchmark.Warmup
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
open class ForEachBenchmark {
    private lateinit var list: MutableList<Int>

    @Setup
    fun setUp() {
        list = mutableListOf()
        repeat(100_000) {
            list.add(it)
        }
    }

    @Benchmark
    fun forEach(blackhole: Blackhole) {
        list.forEach { blackhole.consume(it) }
    }

    @Benchmark
    fun forLoop(blackhole: Blackhole) {
        for (i in 0 until list.size) {
            blackhole.consume(list[i])
        }
    }
}