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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TodoListScreen() {
  val context = LocalContext.current
  val focusManager = LocalFocusManager.current
  val todos = readData(context)
  var todoText by remember { mutableStateOf("") }
  var clickedTodoIndex by remember { mutableIntStateOf(-1) }
  var clickedTodoText by remember { mutableStateOf("") }
  var showEditDialog by remember { mutableStateOf(false) }
  var showDeleteDialog by remember { mutableStateOf(false) }
  var showDetailDialog by remember { mutableStateOf(false) }

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
        value = todoText,
        onValueChange = { todoText = it },
        modifier = Modifier.weight(1f),
        placeholder = { Text("Enter todo") },
      )
      Spacer(modifier = Modifier.width(8.dp))
      Button(
        onClick = {
          if (todoText.isNotBlank()) {
            todos.add(todoText)
            writeData(items = todos, context = context)
            todoText = ""
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
                clickedTodoIndex = index
                clickedTodoText = todo
                showDetailDialog = true
              }
              .weight(1f),
          )
          IconButton(
            onClick = {
              showEditDialog = true
              clickedTodoIndex = index
              clickedTodoText = todo
            },
          ) {
            Icon(
              imageVector = Icons.Default.Edit,
              contentDescription = "Edit",
            )
          }
          IconButton(
            onClick = {
              showDeleteDialog = true
              clickedTodoIndex = index
              clickedTodoText = todo
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
  if (showEditDialog) {
    AlertDialog(
      onDismissRequest = { showEditDialog = false },
      title = { Text(text = "Do you want to update this todo?") },
      text = {
        TextField(
          value = clickedTodoText,
          onValueChange = { clickedTodoText = it },
        )
      },
      confirmButton = {
        Button(
          onClick = {
            showEditDialog = false
            todos[clickedTodoIndex] = clickedTodoText
            writeData(todos, context)
            Toast.makeText(context, "Todo updated", Toast.LENGTH_SHORT).show()
          },
        ) {
          Text(text = "Update")
        }
      },
      dismissButton = {
        Button(
          onClick = {
            showEditDialog = false
          },
        ) {
          Text(text = "Cancel")
        }
      },
    )
  }
  if (showDeleteDialog) {
    AlertDialog(
      onDismissRequest = { showDeleteDialog = false },
      title = { Text(text = "Do you want to delete this todo?") },
      text = { Text(text = todos[clickedTodoIndex]) },
      confirmButton = {
        Button(
          onClick = {
            showDeleteDialog = false
            todos.removeAt(clickedTodoIndex)
            writeData(todos, context)
            Toast.makeText(context, "Todo deleted", Toast.LENGTH_SHORT).show()
          },
        ) {
          Text(text = "Delete")
        }
      },
      dismissButton = {
        Button(
          onClick = {
            showDeleteDialog = false
          },
        ) {
          Text(text = "Cancel")
        }
      },
    )
  }
  if (showDetailDialog) {
    AlertDialog(
      onDismissRequest = { showDetailDialog = false },
      text = { Text(text = todos[clickedTodoIndex]) },
      confirmButton = {
        Button(
          onClick = { showDetailDialog = false },
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