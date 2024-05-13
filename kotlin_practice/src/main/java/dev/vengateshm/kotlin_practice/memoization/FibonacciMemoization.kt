package dev.vengateshm.kotlin_practice.memoization

import kotlin.system.measureTimeMillis

fun main() {
    test(::fibonacciMemoized)
    test(::fibonacciMemoizedV1)
    test(memoized(::fibonacci))
    test(::fibonacci)
}

fun test(fn: (Int) -> Int) {
    val n = 45
    val time = measureTimeMillis {
        println(fn(n))
    }
    println("Time $time ms")
}

fun fibonacci(n: Int): Int {
    require(n >= 0) { "No negative number in a series." }
    if (n == 0 || n == 1) return n
    //println("computing fibonacci($n)")
    return fibonacci(n - 1) + fibonacci(n - 2)
}

private val cache = mutableMapOf<Int, Int>()

fun fibonacciMemoized(n: Int): Int {
    require(n >= 0) { "No negative number in a series." }
    if (n == 0 || n == 1) return n

    if (cache.containsKey(n)) {
        //println("get cached value for $n")
        return cache.getValue(n)
    }

    val result = fibonacciMemoized(n - 1) + fibonacciMemoized(n - 2)
    cache.put(n, result)
    return result
}

fun fibonacciMemoizedV1(n: Int) = cache.getOrPut(n) {
    require(n >= 0) { "No negative number in a series." }
    if (n == 0 || n == 1) return n
    fibonacciMemoized(n - 1) + fibonacciMemoized(n - 2)
}

fun <In, Out> memoized(fn: (In) -> Out): (In) -> Out {
    val cache = mutableMapOf<In, Out>()
    return { it ->
        cache.getOrPut(it) {
            fn(it)
        }
    }
}