package dev.vengateshm.compose_material3

import dev.vengateshm.compose_material3.testing.fibonacci.FibonacciUseCase
import org.junit.Before
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

class FibonacciUseCaseTest {
    private var SUT: FibonacciUseCase? = null

    @Before
    fun setUp() {
        SUT = FibonacciUseCase()
    }

    @Test
    fun test_0() {
        val result = SUT?.compute(0)
        assertEquals(BigInteger("0"), result)
    }

    @Test
    fun test_1() {
        val result = SUT?.compute(1)
        assertEquals(BigInteger("1"), result)
    }

    @Test
    fun test_10() {
        val result = SUT?.compute(10)
        assertEquals(BigInteger("55"), result)
    }
}