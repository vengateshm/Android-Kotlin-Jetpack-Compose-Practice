package dev.vengateshm.compose_material3.apps.todo_list

class TodoListRepositoryImpl(private val fileRepository: FileRepository) : TodoListRepository {
  override fun getTodos(): ArrayList<String> {
    return fileRepository.read()
  }

  override fun saveTodos(todos: ArrayList<String>) {
    return fileRepository.write(todos)
  }
}