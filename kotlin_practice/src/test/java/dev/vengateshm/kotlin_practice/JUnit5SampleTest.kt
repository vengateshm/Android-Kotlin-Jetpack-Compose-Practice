package dev.vengateshm.kotlin_practice

import dev.vengateshm.kotlin_practice.junit5.JUnit5Sample
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class JUnit5SampleTest {

    private val sut = JUnit5Sample()

    @ParameterizedTest(name = "Testing {index} : Input {0} => Expected {1}")
    @CsvSource("radar,true", "civic,true", "12321,true", "good,false")
    fun testIsPalindromeCsvSourceCustomLabel(input: String, expected: Boolean) {
        Assertions.assertEquals(expected, sut.isPalindrome(input))
    }

    @ParameterizedTest
    @CsvSource("radar,true", "civic,true", "12321,true", "good,false")
    fun testIsPalindromeCsvSource(input: String, expected: Boolean) {
        Assertions.assertEquals(expected, sut.isPalindrome(input))
    }

    @ParameterizedTest
    @CsvSource("radar;true", "civic;true", "12321;true", "good;false", delimiter = ';')
    fun testIsPalindromeCsvSourceWithCustomDelimiter(input: String, expected: Boolean) {
        Assertions.assertEquals(expected, sut.isPalindrome(input))
    }

    @ParameterizedTest
    @ValueSource(strings = ["radar", "12321", "civic"])
    fun testIsPalindromeValueSourceWithStringArray(input: String) {
        Assertions.assertTrue(sut.isPalindrome(input))
    }

    @ParameterizedTest
    @MethodSource("rectangleTestData")
    fun testAreaOfRectangleMethodSource(width: Double, height: Double, result: Double) {
        Assertions.assertEquals(result, sut.areaOfRectangle(width, height))
    }

    @ParameterizedTest
    @CsvSource("3.0,2.0,6.0", "4.0,2.0,8.0")
    fun testAreaOfRectangleCsvSource(width: Double, height: Double, result: Double) {
        Assertions.assertEquals(result, sut.areaOfRectangle(width, height))
    }

    companion object {
        @JvmStatic
        fun rectangleTestData(): Array<Array<Any>> {
            return arrayOf(
                arrayOf(3.0, 2.0, 6.0),
                arrayOf(4.0, 2.0, 8.0)
            )
        }
    }
}