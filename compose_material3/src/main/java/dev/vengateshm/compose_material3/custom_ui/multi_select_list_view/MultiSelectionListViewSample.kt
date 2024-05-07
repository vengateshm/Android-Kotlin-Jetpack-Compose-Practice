package dev.vengateshm.compose_material3.custom_ui.multi_select_list_view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun MultiSelectionListViewSample(modifier: Modifier = Modifier) {
    val items = remember { (1..100).toList() }
    val state = rememberMultiSelectionState()
    val selectedItems = remember {
        mutableStateListOf<Int>()
    }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = {
                    val oldState = state.isMultiSelectModeEnabled
                    state.isMultiSelectModeEnabled = !state.isMultiSelectModeEnabled
                    if (oldState) {
                        selectedItems.clear()
                    }
                }) {
                    Text(text = if (state.isMultiSelectModeEnabled) "Done" else "Select")
                }
            }
        }
    ) { paddingValues ->
        MultiSelectionList(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
            state = state,
            items = items,
            selectedItems = selectedItems,
            itemContent = {
                Text(
                    text = it.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            },
            key = { it },
            onClick = {
                if (state.isMultiSelectModeEnabled) {
                    if (it in selectedItems)
                        selectedItems.remove(it)
                    else
                        selectedItems.add(it)
                } else {
                    Toast.makeText(context, "Click $it", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}