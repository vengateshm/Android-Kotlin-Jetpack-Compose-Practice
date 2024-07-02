package dev.vengateshm.compose_material3.coroutines

import dev.vengateshm.compose_material3.testing.couroutines.DataViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.test.assertEquals

class DataViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `verify get data`() = runTest {
        //Dispatchers.setMain(UnconfinedTestDispatcher())

        val dataViewModel = DataViewModel()

        dataViewModel.getData()

        assertEquals(listOf("a", "b", "c"), dataViewModel.list.value)

        //Dispatchers.resetMain()
    }
}

class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) :
    TestWatcher() {
    override fun starting(description: Description?) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}