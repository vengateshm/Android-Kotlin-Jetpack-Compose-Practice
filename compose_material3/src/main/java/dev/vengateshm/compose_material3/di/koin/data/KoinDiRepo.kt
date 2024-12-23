package dev.vengateshm.compose_material3.di.koin.data

interface KoinDiRepo {
    suspend fun getData(): String
}