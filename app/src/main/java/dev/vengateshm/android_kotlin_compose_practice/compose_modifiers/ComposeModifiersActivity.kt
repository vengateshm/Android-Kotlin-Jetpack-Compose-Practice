package dev.vengateshm.android_kotlin_compose_practice.compose_modifiers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

class ComposeModifiersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                var counter by remember {
                    mutableIntStateOf(0)
                }
                var selectedItem by remember {
                    mutableStateOf("")
                }
                val items = remember { mutableStateListOf<String>() }
                Content(
                    onClick = {
                        val newCounterValue = counter + 1
                        counter = newCounterValue
                        items.add("Item ${newCounterValue + 1}")
                    },
                    items = { items.toList() },
                    onItemSelected = {
                        selectedItem = it
                    },
                )
            }
        }
    }
}

@Composable
fun Content(
    onClick: () -> Unit,
    items: () -> List<String>,
    onItemSelected: (String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {
                onClick()
            },
        ) {
            Text(text = "Add new item")
        }
        LazyColumn {
            items(items()) { item ->
                /*Item(value = item, modifier = Modifier.clickable {
                    onItemSelected(item)
                })*/
                Item(
                    value = item,
                    onClick = {
                        onItemSelected(it)
                    },
                )
            }
        }
    }
}

@Composable
fun Item(
    value: String,
    modifier: Modifier,
) {
    Text(text = value, modifier = modifier)
}

@Composable
fun Item(
    value: String,
    onClick: (String) -> Unit,
) {
    Text(text = value, modifier = Modifier.clickable { onClick(value) })
}
