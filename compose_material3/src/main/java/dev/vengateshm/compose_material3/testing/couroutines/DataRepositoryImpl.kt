package dev.vengateshm.compose_material3.testing.couroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class DataRepositoryImpl(
    private val dataSource: DataSource,
    private val ioDispatcher: CoroutineContext = Dispatchers.IO
) : DataRepository {

    private lateinit var _list: MutableList<String>

    private val scope = CoroutineScope(ioDispatcher)

    override fun init() {
        scope.launch {
            _list = mutableListOf()
        }
    }

    override fun insert(list: List<String>) {
        scope.launch {
            repeat(100000 * list.size) {}
            _list.addAll(list)
        }
    }

    override suspend fun fetch(): List<String> = withContext(ioDispatcher) {
        _list
    }

    override suspend fun emit(value: Int) {
        return dataSource.emit(value)
    }

    override fun counts(): Flow<Int> {
        return dataSource.counts()
    }
}