package dev.vengateshm.kotlin_practice.testing

fun main() {
//    fizzBuzz(Int.MAX_VALUE).forEach(::println)
    fizzBuzzSequence(1_000_000).forEach(::println)
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
