package dev.vengateshm.kotlin_practice.arrow_kt

import arrow.core.Either
import java.io.File

fun main() {
//    parse("3")
//    parse("Not a number")
    when (val result = parseToInt("2A")) {
        is Either.Left -> println(result.value.message)
        is Either.Right -> println(result.value)
    }
    val contents = readFile("kotlin-samples/src/main/myFile.txt")
    if (contents is Either.Right) {
        // The file was read successfully.
        println(contents.value)
    } else {
        // An error occurred while reading the file.
        println((contents as? Either.Left)?.value)
    }
}

fun parse(s: String): Int =
    if (s.matches(Regex("-?[0-9]+"))) s.toInt()
    else throw NumberFormatException("$s is not a valid integer.")

fun reciprocal(i: Int): Double =
    if (i == 0) throw IllegalArgumentException("Cannot take reciprocal of 0.")
    else 1.0 / i

fun parseToInt(s: String): Either<NumberFormatException, Int> =
    if (s.matches(Regex("-?[0-9]+")))
        Either.Right(s.toInt())
    else Either.Left(NumberFormatException("$s is not a valid integer."))

fun readFile(fileName: String): Either<Exception, String> {
    return try {
        val file = File(fileName)
        val contents = file.readText()
        Either.Right(contents)
    } catch (e: Exception) {
        Either.Left(e)
    }
}