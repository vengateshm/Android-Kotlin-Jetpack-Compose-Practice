package dev.vengateshm.compose_material3.custom_ui.multi_select_list_view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T> MultiSelectionList(
    modifier: Modifier,
    state: MultiSelectionState,
    items: List<T>,
    selectedItems: List<T>,
    itemContent: @Composable (T) -> Unit,
    key: ((item: T) -> Any)? = null,
    onClick: (T) -> Any
) {
    LazyColumn(modifier = modifier) {
        items(
            items = items,
            key = key
        ) { item: T ->
            MultiSelectionItemContainer(
                modifier = modifier,
                isEnabled = state.isMultiSelectModeEnabled,
                isSelected = item in selectedItems,
                enableMultiSelectionMode = {
                    state.isMultiSelectModeEnabled = it
                },
                onClick = {
                    onClick(item)
                }
            ) {
                itemContent(item)
            }
        }
    }
}