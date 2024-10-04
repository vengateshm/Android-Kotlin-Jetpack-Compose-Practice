package dev.vengateshm.compose_material3.testing.fibonacci

import java.math.BigInteger

class FibonacciUseCase {
    fun compute(index: Int): BigInteger {
        if (index == 0) return BigInteger("0")
        if (index == 1) return BigInteger("1")
        return compute(index - 1).add(compute(index - 2))
    }
}