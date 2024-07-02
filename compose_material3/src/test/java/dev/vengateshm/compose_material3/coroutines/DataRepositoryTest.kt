package dev.vengateshm.compose_material3.coroutines

import dev.vengateshm.compose_material3.testing.couroutines.ColdDataSource
import dev.vengateshm.compose_material3.testing.couroutines.DataRepositoryImpl
import dev.vengateshm.compose_material3.testing.couroutines.HotDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class DataRepositoryTest {

    @Test
    fun `test cold data source`() = runTest(UnconfinedTestDispatcher()) {
        val dataSource = ColdDataSource()
        val repository = DataRepositoryImpl(dataSource)

        repository.init()

        repository.insert(listOf("a"))
        repository.insert(listOf("b"))
        repository.insert(listOf("c"))
        repository.insert(listOf("d"))
        repository.insert(listOf("e", "f", "g", "h"))

        //advanceUntilIdle()

        assertEquals(listOf("a", "b", "c", "d", "e", "f", "g", "h"), repository.fetch())
    }

    @Test
    fun `test cold data source with dispatcher`() = runTest {
        val dataSource = ColdDataSource()
        val repository = DataRepositoryImpl(dataSource, StandardTestDispatcher(testScheduler))

        repository.init()

        repository.insert(listOf("a"))
        repository.insert(listOf("b"))
        repository.insert(listOf("c"))
        repository.insert(listOf("d"))
        repository.insert(listOf("e", "f", "g", "h"))

        //advanceUntilIdle()

        assertEquals(listOf("a", "b", "c", "d", "e", "f", "g", "h"), repository.fetch())
    }

    @Test
    fun `test cold flow`() = runTest {
        val repository = DataRepositoryImpl(ColdDataSource())
        val counts = repository.counts().toList()
        assertEquals(1, counts[0])
    }

    @Test
    fun `test hot flow`() = runTest {
        val repository = DataRepositoryImpl(HotDataSource())

        val list = mutableListOf<Int>()

//        val job = launch(StandardTestDispatcher()) {
        val job = launch(UnconfinedTestDispatcher()) {
            repository.counts().collectLatest {
                list.add(it)
            }
        }

        repository.emit(1)
        job.cancel()

        assertEquals(1, list[0])
    }

    @Test
    fun `test hot flow with backgroundScope`() = runTest {
        val repository = DataRepositoryImpl(HotDataSource())

        val list = mutableListOf<Int>()

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            repository.counts().collectLatest {
                list.add(it)
            }
        }

        repository.emit(1)

        assertEquals(1, list[0])
    }
}