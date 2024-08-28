package dev.vengateshm.kotlin_practice

import dev.vengateshm.kotlin_practice.testing.fizzBuzz
import dev.vengateshm.kotlin_practice.testing.fizzBuzzEmpty
import dev.vengateshm.kotlin_practice.testing.fizzBuzzOutput
import dev.vengateshm.kotlin_practice.testing.fizzBuzzSequence
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class FizzBuzzAcceptanceTests {
    @Test
    fun `prints fizzbuzz up to 100`() {
        var stdOutResult = captureStdOut { print("hello world") }
        assertEquals("hello world", stdOutResult)
        stdOutResult = captureStdOut { println("hello world") }
        assertEquals("hello world\n", stdOutResult)
        stdOutResult = captureStdOut { fizzBuzzEmpty() }
        assertTrue(stdOutResult.isEmpty())
        stdOutResult = captureStdOut { fizzBuzz(20).forEach(::println) }
        stdOutResult = captureStdOut { fizzBuzzSequence(20).forEach(::println) }
        val lines = stdOutResult.lines()
        assertEquals(fizzBuzzOutput.lines(), lines.take(20))
        assertEquals(21, lines.size)
        assertEquals("", lines.last())
        assertEquals("Buzz", lines.drop(19).first())
        assertEquals("Buzz", lines[19])
        assertEquals("", lines[20])
    }

    @Test
    fun `prints fizzbuzz up to 100 1`() {
        val output = ByteArrayOutputStream()
        //fizzBuzz(PrintStream(output))
        fizzBuzz(PrintStream(output), 100)
        val lines = output.toString().lines()
        assertEquals(fizzBuzzOutput.lines(), lines.take(20))
        assertEquals(101, lines.size)
        assertEquals("Buzz", lines[99])
        assertEquals("", lines[100])
    }
}

private fun captureStdOut(block: () -> Unit): String {
    val output = ByteArrayOutputStream()
    val printStream = PrintStream(output)
    val oldStdOut = System.out
    System.setOut(printStream)
    try {
        block()
    } catch (e: Exception) {
        System.setOut(oldStdOut)
    }
    return output.toString("UTF-8")
}