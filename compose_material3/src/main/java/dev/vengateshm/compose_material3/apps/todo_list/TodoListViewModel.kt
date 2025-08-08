package dev.vengateshm.compose_material3.apps.todo_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TodoListViewModel(private val repository: TodoListRepository) : ViewModel() {
  var todos = mutableStateListOf<String>()
    private set

  var todoText by mutableStateOf("")
  var clickedTodoIndex by mutableIntStateOf(-1)
  var clickedTodoText by mutableStateOf("")
  var showEditDialog by mutableStateOf(false)
  var showDeleteDialog by mutableStateOf(false)
  var showDetailDialog by mutableStateOf(false)

  init {
    val items = repository.getTodos()
    todos.addAll(items)
  }

  fun addTodo() {
    todos.add(todoText)
    todoText = ""
    repository.saveTodos(todos = ArrayList(todos))
  }

  fun editTodo() {
    todos[clickedTodoIndex] = clickedTodoText
    repository.saveTodos(todos = ArrayList(todos))
  }

  fun removeTodo(index: Int) {
    todos.removeAt(index)
    repository.saveTodos(todos = ArrayList(todos))
  }
}


class TodoListViewModelFactory(
  private val todoListRepository: TodoListRepository,
) : ViewModelProvider.Factory {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(TodoListViewModel::class.java)) {
      return TodoListViewModel(todoListRepository) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}
