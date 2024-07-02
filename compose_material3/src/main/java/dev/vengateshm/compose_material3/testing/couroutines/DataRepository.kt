package dev.vengateshm.compose_material3.testing.couroutines

import kotlinx.coroutines.flow.Flow

interface DataRepository {
    fun init()
    fun insert(list: List<String>)
    suspend fun fetch(): List<String>
    suspend fun emit(value: Int)
    fun counts(): Flow<Int>
}