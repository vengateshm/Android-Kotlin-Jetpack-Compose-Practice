package dev.vengateshm.compose_material3.testing.fibonacci

import dev.vengateshm.compose_material3.testing.fibonacci.FibonacciUseCaseAsync.Callback
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger

class FibonacciUseCaseCoroutinesCallback(private val dispatcher: CoroutineDispatcher) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun compute(index: Int, callback: Callback?) {
        coroutineScope.launch {
            val result = compute(index)
            callback?.onResult(result)
        }
    }

    private suspend fun compute(index: Int): BigInteger = withContext(dispatcher) {
        when (index) {
            0 -> BigInteger("0")
            1 -> BigInteger("1")
            else -> compute(index - 1).add(compute(index - 2))
        }
    }
}