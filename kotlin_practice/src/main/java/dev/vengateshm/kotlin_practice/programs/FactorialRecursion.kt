package dev.vengateshm.kotlin_practice.programs

import java.math.BigInteger

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
    println()
    factorialRec(0).also(::println)
    factorialRec(1).also(::println)
    factorialRec(2).also(::println)
    factorialRec(3).also(::println)
    factorialRec(4).also(::println)
    factorialRec(5).also(::println)
    println()
    factorialWith(5).also(::println)
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

fun factorialRec(n: Long, accumulator: BigInteger = BigInteger.ONE): BigInteger {
    val partial = accumulator * BigInteger.valueOf(n)
    return if (n == 0L) {
        BigInteger.valueOf(1)
    } else if (n <= 1) {
        partial
    } else {
        factorialRec(n - 1, partial)
    }
}

tailrec fun factorialRec1(n: Long, accumulator: BigInteger = BigInteger.ONE): BigInteger {
    return if (n == 0L) {
        BigInteger.valueOf(1)
    } else if (n <= 1) {
        accumulator
    } else {
        factorialRec(n - 1, accumulator * BigInteger.valueOf(n))
    }
}

fun factorialWith(n: Long): BigInteger {
    return DeepRecursiveFunction<Long, BigInteger> { x ->
        when {
            x <= 1 -> BigInteger.ONE
            else -> x.toBigInteger().multiply(callRecursive(x - 1))
        }
    }.invoke(n)
}