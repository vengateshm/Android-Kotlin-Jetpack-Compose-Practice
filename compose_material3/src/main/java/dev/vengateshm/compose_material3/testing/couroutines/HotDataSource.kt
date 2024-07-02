package dev.vengateshm.compose_material3.testing.couroutines

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class HotDataSource : DataSource {

    private val _mutableSharedFlow = MutableSharedFlow<Int>()

    override suspend fun emit(value: Int) {
        _mutableSharedFlow.emit(value)
    }

    override fun counts(): Flow<Int> {
        return _mutableSharedFlow
    }
}