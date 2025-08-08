package dev.vengateshm.compose_material3.apps.todo_list

interface FileRepository {
  fun read(): ArrayList<String>
  fun write(items: ArrayList<String>)
}