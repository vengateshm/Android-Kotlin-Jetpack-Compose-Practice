package dev.vengateshm.compose_material3.testing.mvvm_local_db

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.vengateshm.compose_material3.testing.mvvm_local_db.room.ToDo
import org.koin.androidx.compose.koinViewModel

@Composable
fun ToDoTestingSample(modifier: Modifier = Modifier) {
    val viewmodel = koinViewModel<ToDoViewModel>()
    val todos by viewmodel.toDos.collectAsStateWithLifecycle(emptyList())
    LaunchedEffect(Unit) {
        viewmodel.insert(
            ToDo(
                title = "Title 1",
                description = "Description 1",
                isCompleted = false,
            ),
        )
        viewmodel.insert(ToDo(title = "Title 2", description = "Description 2", isCompleted = true))
    }

    Column(modifier = modifier) {
        todos.forEach {
            Text(text = it.title)
            Text(text = it.description)
            Checkbox(checked = it.isCompleted, onCheckedChange = null, interactionSource = null)
        }
    }
}
