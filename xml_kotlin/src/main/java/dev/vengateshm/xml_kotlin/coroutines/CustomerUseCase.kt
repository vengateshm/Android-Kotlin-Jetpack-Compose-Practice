package dev.vengateshm.xml_kotlin.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class CustomerUseCase {
    suspend fun makeCustomerPremium() = withContext(Dispatchers.Default) {
        withContext(NonCancellable) {
            makeCustomerPremiumServer()
            makeCustomerPremiumDatabase()
        }
    }

    private suspend fun makeCustomerPremiumServer() {
        delay(2000L)
        logThreadInfo("make customer premium server")
    }

    private suspend fun makeCustomerPremiumDatabase() {
        delay(1000L)
        logThreadInfo("make customer premium database")
    }
}