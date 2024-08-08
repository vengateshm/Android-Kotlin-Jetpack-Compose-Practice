package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(fibonacciRecursion(6))
    println(fibonacciIteration(6))
}

fun fibonacciRecursion(n: Int): Int {
    if (n < 2) return n
    return fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2)
}

fun fibonacciIteration(n: Int): Long {
    if (n <= 1) return n.toLong()
    val fib = arrayOf(0L, 1L)
    for (i in 2..n) {
        val sum = fib[0] + fib[1]
        fib[0] = fib[1]
        fib[1] = sum
    }
    return fib[1]
}