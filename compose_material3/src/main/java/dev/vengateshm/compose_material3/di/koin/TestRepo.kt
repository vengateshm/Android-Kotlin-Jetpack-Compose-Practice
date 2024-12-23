package dev.vengateshm.compose_material3.di.koin

import kotlinx.coroutines.delay

class TestRepo {
    suspend fun getData(): String {
        delay(3000L)
        return "Test!"
    }
}