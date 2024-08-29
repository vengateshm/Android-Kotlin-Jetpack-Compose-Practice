package dev.vengateshm.kotlin_practice

import dev.vengateshm.kotlin_practice.testing.toFizzBuzz
import net.jqwik.api.Arbitraries
import net.jqwik.api.Arbitrary
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Provide
import kotlin.test.assertEquals

class FizzBuzzPropertyTests {

    @Property
    fun `multiples of 15 return FizzBuzz`(@ForAll("multiplesOf15") input: Int) {
        assertEquals("FizzBuzz", input.toFizzBuzz())
    }

    @Property
    fun `multiples of 3 return Fizz`(@ForAll("multiplesOf3Not15") input: Int) {
        assertEquals("Fizz", input.toFizzBuzz())
    }

    @Property
    fun `multiples of 5 return Buzz`(@ForAll("multiplesOf5Not15") input: Int) {
        assertEquals("Buzz", input.toFizzBuzz())
    }

    @Property
    fun `other numbers return their string representation`(
        @ForAll("notMultiplesOf3Or5") input: Int,
    ) {
        assertEquals(input.toString(), input.toFizzBuzz())
    }

    @Provide
    fun multiplesOf15(): Arbitrary<Int> =
        Arbitraries.integers().between(1, Int.MAX_VALUE).filter { it % 15 == 0 }

    @Provide
    fun multiplesOf3Not15(): Arbitrary<Int> =
        Arbitraries.integers().between(1, Int.MAX_VALUE)
            .filter { it % 3 == 0 && it % 15 != 0 }

    @Provide
    fun multiplesOf5Not15(): Arbitrary<Int> =
        Arbitraries.integers().between(1, Int.MAX_VALUE)
            .filter { it % 5 == 0 && it % 15 != 0 }

    @Provide
    fun notMultiplesOf3Or5(): Arbitrary<Int> =
        Arbitraries.integers().between(1, Int.MAX_VALUE)
            .filter { it % 3 != 0 && it % 5 != 0 }
}