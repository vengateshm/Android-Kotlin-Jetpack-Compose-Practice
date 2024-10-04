package dev.vengateshm.compose_material3.testing.fibonacci

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.BigInteger

class FibonacciUseCaseCoroutinesSuspending {
    suspend fun compute(index: Int): BigInteger = withContext(Dispatchers.Default) {
        when (index) {
            0 -> BigInteger("0")
            1 -> BigInteger("1")
            else -> compute(index - 1).add(compute(index - 2))
        }
    }
}