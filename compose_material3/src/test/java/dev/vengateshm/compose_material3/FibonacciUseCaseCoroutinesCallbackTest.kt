package dev.vengateshm.compose_material3

import dev.vengateshm.compose_material3.testing.fibonacci.FibonacciUseCaseAsync
import dev.vengateshm.compose_material3.testing.fibonacci.FibonacciUseCaseCoroutinesCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

class FibonacciUseCaseCoroutinesCallbackTest {
    private var SUT: FibonacciUseCaseCoroutinesCallback? = null
    private var callback: FibonacciUseCaseAsync.Callback? = null
    private var callbackResult: BigInteger? = null

    @OptIn(ExperimentalCoroutinesApi::class)
    private val unconfinedTestDispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(unconfinedTestDispatcher)
        SUT = FibonacciUseCaseCoroutinesCallback(unconfinedTestDispatcher)
        callback = object : FibonacciUseCaseAsync.Callback {
            override fun onResult(result: BigInteger) {
                callbackResult = result
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun test_0() {
        SUT?.compute(0, callback)
        assertEquals(BigInteger("0"), callbackResult)
    }

    @Test
    fun test_1() {
        SUT?.compute(1, callback)
        assertEquals(BigInteger("1"), callbackResult)
    }

    @Test
    fun test_10() {
        SUT?.compute(10, callback)
        assertEquals(BigInteger("55"), callbackResult)
    }

    @Test
    fun test_30() {
        SUT?.compute(30, callback)
        assertEquals(BigInteger("832040"), callbackResult)
    }
}