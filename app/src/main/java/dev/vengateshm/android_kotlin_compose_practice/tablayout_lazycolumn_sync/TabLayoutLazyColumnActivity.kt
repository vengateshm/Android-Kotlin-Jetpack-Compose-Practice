package dev.vengateshm.android_kotlin_compose_practice.tablayout_lazycolumn_sync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class TabLayoutLazyColumnActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    TabLayoutLazyColumn(
                        categories = ListData.categories,
                        items = ListData.items
                    )
                }
            }
        }
    }
}

@Composable
fun TabLayoutLazyColumn(categories: List<Category>, items: List<Item>) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var currentCategory by remember { mutableStateOf("Category 1") }

    val newTabIndex by remember {
        derivedStateOf {
            if (currentCategory != items[lazyListState.firstVisibleItemIndex].category) {
                currentCategory = items[lazyListState.firstVisibleItemIndex].category
                categories.indexOfFirst { it.name == currentCategory }
            } else {
                -1
            }
        }
    }

    LaunchedEffect(newTabIndex) {
        if (newTabIndex > -1) {
            selectedTabIndex = newTabIndex
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            modifier = Modifier.fillMaxWidth(),
            selectedTabIndex = selectedTabIndex
        ) {
            categories.forEachIndexed { index, category ->
                Tab(
                    text = { Text(category.name) },
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        scope.launch {
                            val scrollToIndex = items.indexOfFirst { it.category == category.name }
                            if (scrollToIndex != -1) {
                                //lazyListState.scrollToItem(scrollToIndex)
                                lazyListState.animateScrollToItem(scrollToIndex)
                            }
                        }
                    }
                )
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState
        ) {
            items(items) { item ->
                Text(
                    modifier = Modifier.padding(32.dp),
                    text = item.name
                )
            }
        }
    }
}

object ListData {
    val categories = listOf(
        Category("Category 1"),
        Category("Category 2"),
        Category("Category 3"),
        Category("Category 4"),
        Category("Category 5")
    )

    val items = listOf(
        Item("Item 1", "Category 1"),
        Item("Item 2", "Category 1"),
        Item("Item 3", "Category 1"),
        Item("Item 4", "Category 1"),
        Item("Item 5", "Category 1"),
        Item("Item 6", "Category 2"),
        Item("Item 7", "Category 2"),
        Item("Item 8", "Category 2"),
        Item("Item 9", "Category 2"),
        Item("Item 10", "Category 2"),
        Item("Item 11", "Category 3"),
        Item("Item 12", "Category 3"),
        Item("Item 13", "Category 3"),
        Item("Item 14", "Category 3"),
        Item("Item 15", "Category 3"),
        Item("Item 16", "Category 4"),
        Item("Item 17", "Category 4"),
        Item("Item 18", "Category 4"),
        Item("Item 19", "Category 4"),
        Item("Item 20", "Category 4"),
        Item("Item 21", "Category 5"),
        Item("Item 22", "Category 5"),
        Item("Item 23", "Category 5"),
        Item("Item 24", "Category 5"),
        Item("Item 25", "Category 5"),
    )
}