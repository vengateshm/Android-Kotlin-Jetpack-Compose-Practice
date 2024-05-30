package dev.vengateshm.compose_material3.mvvm_local_db

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dev.vengateshm.compose_material3.mvvm_local_db.di.toDoTestModule
import dev.vengateshm.compose_material3.testing.mvvm_local_db.room.ToDo
import dev.vengateshm.compose_material3.testing.mvvm_local_db.room.ToDoDao
import dev.vengateshm.compose_material3.testing.mvvm_local_db.room.ToDoDatabase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class ToDoDaoTestKoin : KoinTest {
    private val database: ToDoDatabase by inject()
    private val toDoDao: ToDoDao by inject()

    @Before
    fun setUp() {
        stopKoin()
        startKoin {
            androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
            modules(toDoTestModule)
        }
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetToDo() = runTest {
        val todo = ToDo(title = "Test ToDo", description = "Test Description", isCompleted = false)
        toDoDao.insert(todo)
        val todos = toDoDao.getAllTodos().first()
        assertEquals(todos[0].title, todo.title)
    }

    @Test
    fun deleteToDo() = runTest {
        val todo = ToDo(title = "Test ToDo", description = "Test Description", isCompleted = false)
        toDoDao.insert(todo)
        val todos = toDoDao.getAllTodos().first()
        toDoDao.delete(todos[0])
        assertEquals(true, toDoDao.getAllTodos().first().isEmpty())
    }
}