package dev.vengateshm.kotlin_practice

import dev.vengateshm.kotlin_practice.testing.fizzBuzz
import dev.vengateshm.kotlin_practice.testing.toFizzBuzz
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class FizzBuzzTests {
    @Test
    fun `3 is Fizz`() {
        assertEquals("Fizz", 3.toFizzBuzz())
    }

    @Test
    fun `5 is Buzz`() {
        assertEquals("Buzz", 5.toFizzBuzz())
    }

    @Test
    fun `divisible by 3 is Fizz`() {
        assertEquals("Fizz", 3.toFizzBuzz())
        assertEquals("Fizz", 6.toFizzBuzz())
        assertEquals("Fizz", 9.toFizzBuzz())
    }

    @Test
    fun `divisible by 5 is Fizz`() {
        assertEquals("Buzz", 5.toFizzBuzz())
        assertEquals("Buzz", 10.toFizzBuzz())
    }

    @Test
    fun `divisible by 15 is FizzBuzz`() {
        assertEquals("FizzBuzz", 15.toFizzBuzz())
    }

    @Test
    fun `is empty if n is 0`() {
        assertEquals(emptyList<String>(), fizzBuzz(0))
    }

    @Test
    fun `returns numbers up to 2`() {
        assertEquals(listOf("1", "2"), fizzBuzz(2))
    }

    @Test
    fun `returns with 3 as Fizz`() {
        assertEquals(listOf("1", "2", "Fizz"), fizzBuzz(3))
    }

    @Test
    fun `returns with 3 as Fizz, 5 as Buzz`() {
        assertEquals(listOf("1", "2", "Fizz", "4", "Buzz"), fizzBuzz(5))
    }

    @Test
    fun `returns with 3 as Fizz, 5 as Buzz, divisible by 3 as Fizz`() {
        assertEquals(listOf("1", "2", "Fizz", "4", "Buzz", "Fizz"), fizzBuzz(6))
    }
}