package dev.vengateshm.android_kotlin_compose_practice.compose_apis

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.debounce

@Composable
fun SnapShotFlowSample() {
    val lazyListState = rememberLazyListState()
    LaunchedEffect(key1 = lazyListState) {
        // Convert State to Flow emits only if change in value
        snapshotFlow {
            lazyListState.firstVisibleItemIndex
        }
            .debounce(500)
            .collect {
                println("Offset $it")
            }
    }
    LazyColumn(
        state = lazyListState
    ) {
        items(100) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Item $it")
            }
        }
    }
}