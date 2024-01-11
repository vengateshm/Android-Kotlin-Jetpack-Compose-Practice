package dev.vengateshm.android_kotlin_compose_practice.generic_composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// Generic lazy column which conforms to type AppUiModel
@Composable
fun <T : AppUiModel> AppLazyColumn(
    modifier: Modifier = Modifier,
    items: List<T>,
    itemContent: @Composable (T) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(items, key = { itemForKey ->
            itemForKey.id()
        }) { item ->
            itemContent(item)
        }
    }
}

