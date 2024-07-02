package dev.vengateshm.compose_material3.testing.couroutines

import kotlinx.coroutines.flow.Flow

interface DataSource {
    suspend fun emit(value: Int)
    fun counts(): Flow<Int>
}