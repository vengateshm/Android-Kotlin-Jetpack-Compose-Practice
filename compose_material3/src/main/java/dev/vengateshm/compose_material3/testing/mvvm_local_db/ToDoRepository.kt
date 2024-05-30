package dev.vengateshm.compose_material3.testing.mvvm_local_db

import dev.vengateshm.compose_material3.testing.mvvm_local_db.room.ToDo
import dev.vengateshm.compose_material3.testing.mvvm_local_db.room.ToDoDao

class ToDoRepository(private val toDoDao: ToDoDao) {
    suspend fun getAllTodos() = toDoDao.getAllTodos()

    suspend fun insert(todo: ToDo) {
        toDoDao.insert(todo)
    }

    suspend fun delete(todo: ToDo) {
        toDoDao.delete(todo)
    }

    suspend fun update(todo: ToDo) {
        toDoDao.update(todo)
    }
}