package dev.vengateshm.compose_material3.api_kotlin.mutex

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class BankSingleton private constructor() {

    private val mutex = Mutex()
    private var _currentBalance = mutableIntStateOf(0)
    val currentBalance: State<Int> = _currentBalance

    suspend fun spend(amount: Int = 500) {
        mutex.withLock {
            if (amount > _currentBalance.intValue) return
            delay(1000)
            _currentBalance.intValue -= amount
        }
    }

    suspend fun deposit() {
        mutex.withLock {
            delay(1000)
            _currentBalance.intValue += 1000
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: BankSingleton? = null

        fun getInstance(): BankSingleton {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: BankSingleton().also { INSTANCE = it }
            }
        }
    }
}