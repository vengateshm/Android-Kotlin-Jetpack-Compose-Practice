package dev.vengateshm.compose_material3.testing.couroutines

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ColdDataSource : DataSource {
    override suspend fun emit(value: Int) {

    }

    override fun counts(): Flow<Int> {
        return flowOf(1, 2, 3, 4, 5)
    }
}