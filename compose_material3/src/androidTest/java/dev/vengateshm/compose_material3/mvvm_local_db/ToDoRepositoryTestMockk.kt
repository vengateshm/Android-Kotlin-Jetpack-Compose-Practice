package dev.vengateshm.compose_material3.mvvm_local_db

import dev.vengateshm.compose_material3.testing.mvvm_local_db.ToDoRepository
import dev.vengateshm.compose_material3.testing.mvvm_local_db.room.ToDo
import dev.vengateshm.compose_material3.testing.mvvm_local_db.room.ToDoDao
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class ToDoRepositoryTestMockk {
    private val toDoDao = mockk<ToDoDao>()
    private lateinit var toDoRepository: ToDoRepository

    @Before
    fun setUp() {
        toDoRepository = ToDoRepository(toDoDao)
    }

    @Test
    fun insertToDo_insertsToDo() = runTest {
        val todo = ToDo(title = "Test ToDo", description = "Test Description", isCompleted = false)
        coEvery { toDoDao.insert(todo) } just Runs
        toDoRepository.insert(todo)
        coVerify { toDoDao.insert(todo) }
    }

    @Test
    fun deleteToDo_deletesToDo() = runTest {
        val todo = ToDo(title = "Test ToDo", description = "Test Description", isCompleted = false)

        coEvery { toDoDao.delete(todo) } just Runs

        toDoRepository.delete(todo)

        coVerify { toDoDao.delete(todo) }
    }

    @Test
    fun updateToDo_updatesToDo() = runTest {
        val todo = ToDo(title = "Test ToDo", description = "Test Description", isCompleted = false)

        coEvery { toDoDao.update(todo) } just Runs

        toDoRepository.update(todo)

        coVerify { toDoDao.update(todo) }
    }

    @Test
    fun getAllTodos_returnsTodos() = runTest {
        val todoList = listOf(
            ToDo(
                id = 1,
                title = "Test ToDo 1",
                description = "Test Description 1",
                isCompleted = false
            )
        )

        coEvery { toDoDao.getAllTodos() } returns flowOf(todoList)

        val result = toDoRepository.getAllTodos().first()

        assertEquals(todoList, result)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}