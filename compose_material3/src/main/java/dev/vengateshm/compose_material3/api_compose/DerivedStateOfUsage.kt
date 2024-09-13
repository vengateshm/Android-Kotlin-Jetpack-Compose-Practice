package dev.vengateshm.compose_material3.api_compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val fruits = listOf(
    "Apple", "Banana", "Orange", "Mango", "Grapes",
    "Watermelon", "Pineapple", "Strawberry", "Kiwi", "Pear",
    "Peach", "Plum", "Lemon", "Lime", "Cherry", "Blueberry",
)

@Composable
fun DerivedStateOfUsage() {
    var searchQuery by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text(text = "Search") },
        )
        FruitList(searchQuery = searchQuery)
    }
}

@Composable
fun FruitList(
    searchQuery: String,
) {
    val filteredItems by rememberFruitList(searchQuery)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        items(filteredItems) {
            Text(
                text = it,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}

@Composable
fun rememberFruitList(searchQuery: String): State<List<String>> {
    val updatedQuery by rememberUpdatedState(newValue = searchQuery)
    return remember {
        derivedStateOf {
            fruits.filter { it.contains(updatedQuery.trim(), ignoreCase = true) }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DerivedStateOfUsagePreview() {
    MaterialTheme {
        Surface {
            DerivedStateOfUsage()
        }
    }
}