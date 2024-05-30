package dev.vengateshm.compose_material3.testing.mvvm_local_db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vengateshm.compose_material3.testing.mvvm_local_db.room.ToDo
import kotlinx.coroutines.launch

class ToDoViewModel(private val repository: ToDoRepository) : ViewModel() {

    suspend fun getAllToDos() = repository.getAllTodos()

    fun insert(todo: ToDo) = viewModelScope.launch {
        repository.insert(todo)
    }

    fun delete(todo: ToDo) = viewModelScope.launch {
        repository.delete(todo)
    }

    fun update(todo: ToDo) = viewModelScope.launch {
        repository.update(todo)
    }
}