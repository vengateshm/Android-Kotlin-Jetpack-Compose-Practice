package dev.vengateshm.compose_material3.apps.todo_list

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TodoListScreen() {
  val context = LocalContext.current
  val focusManager = LocalFocusManager.current

  val fileRepository = remember { FileRepositoryImpl(context = context) }
  val todoListRepository = remember { TodoListRepositoryImpl(fileRepository = fileRepository) }
  val viewModelFactory = remember { TodoListViewModelFactory(todoListRepository) }
  val viewModel: TodoListViewModel = viewModel(factory = viewModelFactory)

  val todos = viewModel.todos

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      TextField(
        value = viewModel.todoText,
        onValueChange = { viewModel.todoText = it },
        modifier = Modifier.weight(1f),
        placeholder = { Text("Enter todo") },
      )
      Spacer(modifier = Modifier.width(8.dp))
      Button(
        onClick = {
          if (viewModel.todoText.isNotBlank()) {
            viewModel.addTodo()
            focusManager.clearFocus()
          } else {
            Toast.makeText(context, "Please enter a todo", Toast.LENGTH_SHORT).show()
          }
        },
      ) {
        Text("Save")
      }
    }
    LazyColumn {
      itemsIndexed(todos) { index, todo ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Text(
            text = todo,
            modifier = Modifier
              .clickable {
                viewModel.apply {
                  showDetailDialog = true
                  clickedTodoIndex = index
                  clickedTodoText = todo
                }
              }
              .weight(1f),
          )
          IconButton(
            onClick = {
              viewModel.apply {
                showEditDialog = true
                clickedTodoIndex = index
                clickedTodoText = todo
              }
            },
          ) {
            Icon(
              imageVector = Icons.Default.Edit,
              contentDescription = "Edit",
            )
          }
          IconButton(
            onClick = {
              viewModel.apply {
                showDeleteDialog = true
                clickedTodoIndex = index
                clickedTodoText = todo
              }
            },
          ) {
            Icon(
              imageVector = Icons.Default.Delete,
              contentDescription = "Delete",
            )
          }
        }
      }
    }
  }
  if (viewModel.showEditDialog) {
    AlertDialog(
      onDismissRequest = { viewModel.showEditDialog = false },
      title = { Text(text = "Do you want to update this todo?") },
      text = {
        TextField(
          value = viewModel.clickedTodoText,
          onValueChange = { viewModel.clickedTodoText = it },
        )
      },
      confirmButton = {
        Button(
          onClick = {
            viewModel.showEditDialog = false
            viewModel.editTodo()
            Toast.makeText(context, "Todo updated", Toast.LENGTH_SHORT).show()
          },
        ) {
          Text(text = "Update")
        }
      },
      dismissButton = {
        Button(
          onClick = {
            viewModel.showEditDialog = false
          },
        ) {
          Text(text = "Cancel")
        }
      },
    )
  }
  if (viewModel.showDeleteDialog) {
    AlertDialog(
      onDismissRequest = { viewModel.showDeleteDialog = false },
      title = { Text(text = "Do you want to delete this todo?") },
      text = { Text(text = todos[viewModel.clickedTodoIndex]) },
      confirmButton = {
        Button(
          onClick = {
            viewModel.apply {
              removeTodo(clickedTodoIndex)
              showDeleteDialog = false
            }
            Toast.makeText(context, "Todo deleted", Toast.LENGTH_SHORT).show()
          },
        ) {
          Text(text = "Delete")
        }
      },
      dismissButton = {
        Button(
          onClick = {
            viewModel.showDeleteDialog = false
          },
        ) {
          Text(text = "Cancel")
        }
      },
    )
  }
  if (viewModel.showDetailDialog) {
    AlertDialog(
      onDismissRequest = { viewModel.showDetailDialog = false },
      text = { Text(text = todos[viewModel.clickedTodoIndex]) },
      confirmButton = {
        Button(
          onClick = { viewModel.showDetailDialog = false },
        ) {
          Text("Ok")
        }
      },
    )
  }
}

@Preview
@Composable
private fun TodoListScreenPreview() {
  MaterialTheme {
    TodoListScreen()
  }
}