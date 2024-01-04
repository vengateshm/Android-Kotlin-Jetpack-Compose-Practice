package dev.vengateshm.kotlin_practice.exceptions

import arrow.core.flatMap
import arrow.core.raise.result
import dev.vengateshm.kotlin_practice.std_library_samples.contents
import java.io.File
import java.io.IOException


fun readContentFromFile(fileName: String): String {
    return try {
        File(fileName).readText()
    } catch (e: IOException) {
        println("Error reading the file: ${e.message}")
        throw e
    }
}

fun readContentFromFile1(fileName: String): Result<String> {
    return try {
        Result.success(File(fileName).readText())
    } catch (e: IOException) {
        println("Error reading the file: ${e.message}")
        Result.failure(e)
    }
}

fun transformContent(content: String): CalculationInput {
    val numbers = content.split(",").mapNotNull { it.toIntOrNull() }
    if (numbers.size != 2) throw Exception("Invalid input format")
    return CalculationInput(numbers[0], numbers[1])
}

fun transformContent1(content: String): Result<CalculationInput> {
    val numbers = content.split(",").mapNotNull { it.toIntOrNull() }
    if (numbers.size != 2) return Result.failure(Exception("Invalid input format"))
    return Result.success(CalculationInput(numbers[0], numbers[1]))
}

data class CalculationInput(val a: Int, val b: Int)

fun divide(dividend: Int, divisor: Int): Int {
    if (divisor == 0) throw ArithmeticException("Division by zero is not allowed")
    return dividend / divisor
}

fun divide1(dividend: Int, divisor: Int): Result<Int> {
    if (divisor == 0) return Result.failure(ArithmeticException("Division by zero is not allowed"))
    return Result.success(dividend / divisor)
}

fun main() {
    // Java style
//    try {
//        val content = readContentFromFile("input.txt")
//        val numbers = transformContent(content)
//        val quotient = divide(numbers.a, numbers.b)
//        println("The quotient of the division is $quotient")
//    } catch (e: IOException) {
//        println("Error reading the file: ${e.message}")
//    } catch (e: Exception) {
//        println("Error: ${e.message}")
//    }

    // Kotlin style
//    runCatching {
//        val content = readContentFromFile("input.txt")
//        val numbers = transformContent(content)
//        val quotient = divide(numbers.a, numbers.b)
//        println("The quotient of the division is $quotient")
//    }.onFailure {
//        println("Error: ${it.message}")
//    }

    // Using Result<> as return type
    // Fold
//    val contentResult = readContentFromFile1("input.txt")
//    contentResult.fold(
//        onSuccess = { content ->
//            println("The content of the file is $content")
//            transformContent1(content).fold(
//                onSuccess = { input ->
//                    val quotient = divide1(input.a, input.b)
//                    quotient.fold(
//                        onSuccess = { result ->
//                            println("The quotient of the division is $result")
//                        },
//                        onFailure = { error ->
//                            println("Error in division: ${error.message}")
//                        }
//                    )
//                },
//                onFailure = { error ->
//                    println("Error transforming the content: ${error.message}")
//                }
//            )
//        },
//        onFailure = { error ->
//            println("Error reading the file: ${error.message}")
//        }
//    )

    // Using map and fold
//    val result = readContentFromFile1("input.txt").map { content ->
//        transformContent1(content).map { numbers ->
//            divide1(numbers.a, numbers.b)
//        }
//    }
//    result.fold(
//        onSuccess = { content ->
//            content.fold(
//                onSuccess = { result ->
//                    println("Result $result")
//                },
//                onFailure = { error ->
//                    println("Error: ${error.message}")
//                }
//            )
//        },
//        onFailure = { error ->
//            println("Error: ${error.message}")
//        }
//    )

    // Using flatMap
//    readContentFromFile1("input.txt").flatMap { content ->
//        transformContent1(content).flatMap { numbers ->
//            divide1(numbers.a, numbers.b)
//        }
//    }.fold(
//        { result -> println("The result is $result") },
//        { error -> println("Error: ${error.message}") }
//    )

    result {
//        val content = readContentFromFile1("input.txt").bind()
//        println("Content $content")
//        val numbers = transformContent1(content).bind()
//        println("Numbers $numbers")
//        val result = divide1(numbers.a, numbers.b).bind()
//        println("Result $result")
        val content = readContentFromFile1("input.txt").bind()
        println("Content $content")
        val numbers = transformContent1(content).bind()
        divide1(numbers.a, numbers.b).bind()
    }.fold(
        onSuccess = { println("Result: $it") },
        onFailure = { println("Error: $it") }
    )
}