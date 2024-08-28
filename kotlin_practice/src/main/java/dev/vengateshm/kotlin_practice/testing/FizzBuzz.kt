package dev.vengateshm.kotlin_practice.testing

import java.io.PrintStream

fun main() {
//    fizzBuzz(Int.MAX_VALUE).forEach(::println)
//    fizzBuzzSequence(1_000_000).forEach(::println)
//    fizzBuzz(System.out)
//    fizzBuzz(System.out, Int.MAX_VALUE)
//    fizzBuzz1(System.out, 10)
//    fizzBuzz2(System.out, 10)
    fizzBuzz3(System.out, 10)
}

fun fizzBuzzEmpty() {

}

fun fizzBuzz(n: Int): List<String> {
    return (1..n).map { it.toFizzBuzz() }
}

fun fizzBuzzSequence(n: Int): List<String> = generateFizzBuzzSequence().take(n).toList()

fun generateFizzBuzzSequence(): Sequence<String> {
    return generateSequence(1L) { it + 1 }.map { it.toFizzBuzz() }
}

internal fun Int.toFizzBuzz(): String = when {
    this % 15 == 0 -> "FizzBuzz"
    this % 3 == 0 -> "Fizz"
    this % 5 == 0 -> "Buzz"
    else -> this.toString()
}

internal fun Long.toFizzBuzz(): String = when {
    this % 15L == 0L -> "FizzBuzz"
    this % 3L == 0L -> "Fizz"
    this % 5L == 0L -> "Buzz"
    else -> this.toString()
}

val fizzBuzzOutput = """
    |1
    |2
    |Fizz
    |4
    |Buzz
    |Fizz
    |7
    |8
    |Fizz
    |Buzz
    |11
    |Fizz
    |13
    |14
    |FizzBuzz
    |16
    |17
    |Fizz
    |19
    |Buzz
""".trimMargin()

fun fizzBuzz(out: PrintStream) {
    val lines = fizzBuzzLines().joinToString(separator = "\n")
    out.println(lines)
}

fun fizzBuzzLines(): List<String> = (1..100).map { it.toFizzBuzz() }

fun fizzBuzz(out: PrintStream, rounds: Int) {
    val lines = fizzBuzzLines(rounds)
    for (line in lines) {
        out.println(line)
    }
}

fun fizzBuzzLines(rounds: Int): Iterable<String> {
    return object : Iterable<String> {
        override fun iterator(): Iterator<String> {
            var i = 1
            return object : Iterator<String> {
                override fun hasNext(): Boolean {
                    return i <= rounds
                }

                override fun next(): String {
                    return i++.toFizzBuzz()
                }
            }
        }
    }
}

fun fizzBuzz1(out: PrintStream, rounds: Int) {
    val lines = fizzBuzzLines1(rounds)
    for (line in lines) {
        out.println(line)
    }
}

fun fizzBuzzLines1(rounds: Int): Sequence<String> =
    object : Sequence<String> {
        var i = 1
        override fun iterator(): Iterator<String> {
            return object : Iterator<String> {
                override fun hasNext(): Boolean = i <= rounds
                override fun next(): String = i++.toFizzBuzz()
            }
        }
    }

fun fizzBuzz2(out: PrintStream, rounds: Int) {
    val lines = fizzBuzzLines2(rounds)
    for (line in lines) {
        out.println(line)
    }
}

fun fizzBuzzLines2(rounds: Int): Sequence<String> =
    generateSequence(1) { it + 1 }.map { it.toFizzBuzz() }.take(rounds)


fun fizzBuzz3(out: PrintStream, rounds: Int) {
    val lines = fizzBuzzLines3(rounds)
    for (line in lines) {
        out.println(line)
    }
}

fun fizzBuzzLines3(rounds: Int): Sequence<String> {
    return object : Sequence<Int> {
        override fun iterator(): Iterator<Int> {
            var i = 0
            return object : Iterator<Int> {
                override fun hasNext(): Boolean = true
                override fun next(): Int = i++
            }
        }
    }.map { it.toFizzBuzz() }
}