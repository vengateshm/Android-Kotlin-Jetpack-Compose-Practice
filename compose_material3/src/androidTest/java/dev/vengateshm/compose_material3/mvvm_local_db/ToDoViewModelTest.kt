package dev.vengateshm.compose_material3.mvvm_local_db

import dev.vengateshm.compose_material3.testing.mvvm_local_db.ToDoRepository
import dev.vengateshm.compose_material3.testing.mvvm_local_db.ToDoViewModel
import dev.vengateshm.compose_material3.testing.mvvm_local_db.room.ToDo
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ToDoViewModelTest {
    private lateinit var viewModel: ToDoViewModel
    private lateinit var repository: ToDoRepository
    private val todosFlow = MutableStateFlow<List<ToDo>>(emptyList())

    @Before
    fun setUp() {
        repository = mockk {
            coEvery { toDos } returns todosFlow
            coEvery { insert(any()) } just Runs
            coEvery { update(any()) } just Runs
            coEvery { delete(any()) } just Runs
        }
        viewModel = ToDoViewModel(repository)
    }

    @Test
    fun insertToDo_callsRepositoryInsert() = runTest {
        val todo = ToDo(title = "Test ToDo", description = "Test Description", isCompleted = false)

        //coEvery { repository.insert(todo) } just Runs

        viewModel.insert(todo)

        coVerify { repository.insert(todo) }
    }

    @Test
    fun deleteToDo_callsRepositoryDelete() = runTest {
        val todo = ToDo(title = "Test ToDo", description = "Test Description", isCompleted = false)

        //coEvery { repository.delete(todo) } just Runs

        viewModel.delete(todo)

        coVerify { repository.delete(todo) }
    }

    @Test
    fun updateToDo_callsRepositoryUpdate() = runTest {
        val todo = ToDo(title = "Test ToDo", description = "Test Description", isCompleted = false)

        //coEvery { repository.update(todo) } just Runs

        viewModel.update(todo)

        coVerify { repository.update(todo) }
    }

    @Test
    fun getAllTodos_returnsTodos() = runTest {
        val todo = ToDo(title = "Test ToDo", description = "Test Description", isCompleted = false)
        todosFlow.value = listOf(todo)

        val todos = viewModel.toDos.first()

        assertEquals(todo, todos[0])
    }
}