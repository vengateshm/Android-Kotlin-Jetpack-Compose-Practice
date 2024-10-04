package dev.vengateshm.compose_material3

import dev.vengateshm.compose_material3.testing.fibonacci.FibonacciUseCaseAsync
import org.junit.Before
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

class FibonacciUseCaseAsyncTest {
    private var SUT: FibonacciUseCaseAsync? = null
    private var callback: FibonacciUseCaseAsync.Callback? = null
    private var callbackResult: BigInteger? = null

    @Before
    fun setUp() {
        SUT = FibonacciUseCaseAsync()
        callback = object : FibonacciUseCaseAsync.Callback {
            override fun onResult(result: BigInteger) {
                callbackResult = result
            }
        }
    }

    @Test
    fun test_0() {
        SUT?.compute(0, callback)
        Thread.sleep(10)
        assertEquals(BigInteger("0"), callbackResult)
    }

    @Test
    fun test_1() {
        SUT?.compute(1, callback)
        Thread.sleep(10)
        assertEquals(BigInteger("1"), callbackResult)
    }

    @Test
    fun test_10() {
        SUT?.compute(10, callback)
        Thread.sleep(100)
        assertEquals(BigInteger("55"), callbackResult)
    }
}