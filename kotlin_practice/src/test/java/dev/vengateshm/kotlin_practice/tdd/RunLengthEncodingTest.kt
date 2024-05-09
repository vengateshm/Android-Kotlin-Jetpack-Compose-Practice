package dev.vengateshm.kotlin_practice.tdd

import dev.vengateshm.kotlin_practice.testing.tdd.runLengthEncoding
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RunLengthEncodingTest {
    @Test
    fun zero() {
        assertEquals("", runLengthEncoding(""))
    }

    @Test
    fun one() {
        assertEquals("1a", runLengthEncoding("a"))
        assertEquals("1b", runLengthEncoding("b"))
    }

    @Test
    fun `one character`() {
        assertEquals("3a", runLengthEncoding("aaa"))
        assertEquals("2b", runLengthEncoding("bb"))
    }

    @Test
    fun many() {
        assertEquals("3a2b", runLengthEncoding("aaabb"))
        assertEquals("3a2b4a2c", runLengthEncoding("aaabbaaaacc"))
    }

    @Test
    fun exception() {
        assertThrows<IllegalArgumentException> {
            runLengthEncoding("1a")
        }
        assertThrows<IllegalArgumentException> {
            runLengthEncoding("aaa4bb")
        }
    }
}