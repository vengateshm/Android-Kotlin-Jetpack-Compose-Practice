package dev.vengateshm.compose_material3.di.koin.domain

import dev.vengateshm.compose_material3.di.koin.data.KoinDiRepo

class KoinDiUseCase(private val koinDiRepo: KoinDiRepo) {
    suspend fun getData(): String {
        return koinDiRepo.getData()
    }
}