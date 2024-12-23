package dev.vengateshm.compose_material3.di.koin.data

import kotlinx.coroutines.delay

class KoinDiRepoImpl : KoinDiRepo {
    override suspend fun getData(): String {
        delay(3000L)
        return "Hello"
    }
}