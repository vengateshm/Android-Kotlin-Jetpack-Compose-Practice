package dev.vengateshm.compose_material3.apps.todo_list

interface TodoListRepository {
  fun getTodos(): ArrayList<String>
  fun saveTodos(todos: ArrayList<String>)
}