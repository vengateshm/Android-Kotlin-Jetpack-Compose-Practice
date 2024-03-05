package dev.vengateshm.android_kotlin_compose_practice.compose_apis

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun RememberWithKeyVsDerivedStateOf() {
    val lazyListState = rememberLazyListState()

    var isEnabled by remember {
        mutableStateOf(true)
    }

    Scaffold(floatingActionButton = {
        ScrollToTopButton(
            state = lazyListState,
            isEnabled = isEnabled
        )
    }) { paddingValues ->
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            repeat(100) { index ->
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                isEnabled = false
                            }
                            .padding(16.dp),
                        text = "Item ${index + 1}"
                    )
                }
            }
        }
    }
}

@Composable
fun ScrollToTopButton(
    state: LazyListState,
    isEnabled: Boolean
) {
    //val showScrollToTopButton = state.firstVisibleItemIndex > 5

    // When output change is less than input change
    //val showScrollToTopButton by remember { derivedStateOf { state.firstVisibleItemIndex >= 5 } }

    //val showScrollToTopButton = remember(state.firstVisibleItemIndex) { state.firstVisibleItemIndex >= 5 }

    val showScrollToTopButton by remember(isEnabled) { derivedStateOf { state.firstVisibleItemIndex >= 5 && isEnabled } }

    val scope = rememberCoroutineScope()

    if (showScrollToTopButton) {
        FloatingActionButton(
            onClick = {
                scope.launch {
                    state.animateScrollToItem(0)
                }
            }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = null
            )
        }
    }
}