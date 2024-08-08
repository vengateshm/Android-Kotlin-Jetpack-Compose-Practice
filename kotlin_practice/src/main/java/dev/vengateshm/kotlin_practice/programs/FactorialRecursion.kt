package dev.vengateshm.kotlin_practice.programs

fun main() {
    factorialRecursion(0).also(::println)
    factorialRecursion(1).also(::println)
    factorialRecursion(2).also(::println)
    factorialRecursion(5).also(::println)
    println()
    factorialIteration(0).also(::println)
    factorialIteration(1).also(::println)
    factorialIteration(2).also(::println)
    factorialIteration(5).also(::println)
}

fun factorialRecursion(n: Int): Int {
    if (n == 0) return 1
    if (n < 2) return n
    return n * factorialRecursion(n - 1)
}

fun factorialIteration(n: Int): Int {
    if (n == 0) return 1
    var result = 1
    for (i in 2..n) {
        result *= i
    }
    return result
}