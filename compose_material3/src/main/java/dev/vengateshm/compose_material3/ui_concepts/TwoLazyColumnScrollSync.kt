package dev.vengateshm.compose_material3.ui_concepts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TwoLazyColumnScrollSync(modifier: Modifier = Modifier) {
    val listState1 = rememberLazyListState()
    val listState2 = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

//    LaunchedEffect(listState1.firstVisibleItemIndex) {
//        coroutineScope.launch {
//            if (listState1.isScrollInProgress) {
//                listState2.animateScrollToItem(
//                    listState1.firstVisibleItemIndex,
//                    listState1.firstVisibleItemScrollOffset
//                )
//            }
//        }
//    }
//
//    LaunchedEffect(listState2.firstVisibleItemIndex) {
//        coroutineScope.launch {
//            if (listState2.isScrollInProgress) {
//                listState1.animateScrollToItem(
//                    listState2.firstVisibleItemIndex,
//                    listState2.firstVisibleItemScrollOffset
//                )
//            }
//        }
//    }

    LaunchedEffect(listState1) {
        snapshotFlow { listState1.firstVisibleItemScrollOffset }
            .collectLatest {
                if (listState1.isScrollInProgress) {
                    listState2.scrollToItem(
                        listState1.firstVisibleItemIndex,
                        listState1.firstVisibleItemScrollOffset
                    )
                }
            }
    }
    LaunchedEffect(listState2) {
        snapshotFlow { listState2.firstVisibleItemScrollOffset }
            .collectLatest {
                if (listState2.isScrollInProgress) {
                    listState1.requestScrollToItem(
                        listState2.firstVisibleItemIndex,
                        listState2.firstVisibleItemScrollOffset
                    )
                }
            }
    }

    Row(Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState1,
            modifier = Modifier.weight(1f)
        ) {
            items(100) { index ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(if (index % 2 == 0) Color.LightGray else Color.White)
                ) {
                    Text(text = "Item $index")
                }
            }
        }

        LazyColumn(
            state = listState2,
            modifier = Modifier.weight(1f)
        ) {
            items(100) { index ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(if (index % 2 == 0) Color.LightGray else Color.White)
                ) {
                    Text(text = "Item $index")
                }
            }
        }
    }
}

@Preview
@Composable
private fun TwoLazyColumnScrollSyncPreview() {
    TwoLazyColumnScrollSync()
}