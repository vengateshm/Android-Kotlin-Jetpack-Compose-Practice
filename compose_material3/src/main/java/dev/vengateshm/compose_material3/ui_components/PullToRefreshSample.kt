package dev.vengateshm.compose_material3.ui_components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshSample() {
    var itemCount by remember { mutableIntStateOf(15) }
    var isRefreshing by remember { mutableStateOf(false) }
    var state = rememberPullToRefreshState()
    val coroutineScope = rememberCoroutineScope()
    val onRefresh: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            delay(3000)
            itemCount += 5
            isRefreshing = false
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Title")
                },
                actions = {
                    IconButton(onClick = onRefresh) {
                        Icon(Icons.Filled.Refresh, "Trigger Refresh")
                    }
                },
            )
        },
    ) {
        PullToRefreshBox(
            modifier = Modifier.padding(it),
            state = state,
            onRefresh = onRefresh,
            isRefreshing = isRefreshing,
            indicator = {
                PullToRefreshDefaults.IndicatorBox(
                    state = state,
                    isRefreshing = isRefreshing,
                    modifier = Modifier.align(Alignment.TopCenter),
                    elevation = 0.dp,
                ) {
                    if (isRefreshing) {
                        CircularProgressIndicator()
                    } else {
                        CircularProgressIndicator(
                            progress = { state.distanceFraction },
                            trackColor = Color.LightGray,
                            gapSize = 1.dp,
                        )
                    }
                }
            },
        ) {
            LazyColumn(Modifier.fillMaxSize()) {
                items(itemCount) {
                    ListItem({ Text(text = "Item ${itemCount - it}") })
                }
            }
        }
    }
}

@Preview
@Composable
private fun PullToRefreshSamplePreview() {
    PullToRefreshSample()
}