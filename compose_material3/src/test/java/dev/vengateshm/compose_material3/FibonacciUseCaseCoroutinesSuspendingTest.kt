package dev.vengateshm.compose_material3

import dev.vengateshm.compose_material3.testing.fibonacci.FibonacciUseCaseCoroutinesSuspending
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class FibonacciUseCaseCoroutinesSuspendingTest {
    private var SUT: FibonacciUseCaseCoroutinesSuspending? = null

    private val unconfinedTestDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(unconfinedTestDispatcher)
        SUT = FibonacciUseCaseCoroutinesSuspending()
    }
    
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun test_0() {
        runTest {
            val result = SUT?.compute(0)
            assertEquals(BigInteger("0"), result)
        }
    }

    @Test
    fun test_1() {
        runTest {
            val result = SUT?.compute(1)
            assertEquals(BigInteger("1"), result)
        }
    }

    @Test
    fun test_10() {
        runTest {
            val result = SUT?.compute(10)
            assertEquals(BigInteger("55"), result)
        }
    }

    @Test
    fun test_30() {
        runTest {
            val result = SUT?.compute(30)
            assertEquals(BigInteger("832040"), result)
        }
    }
}