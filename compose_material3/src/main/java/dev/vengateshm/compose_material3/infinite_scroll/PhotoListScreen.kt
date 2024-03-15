package dev.vengateshm.compose_material3.infinite_scroll

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun PhotoListScreen(viewModel: PhotoListViewModel) {
    val photoList = viewModel.photoList
    val listState = rememberLazyListState()

    val isItemScrollToEnd by remember {
        derivedStateOf {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == listState.layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(key1 = isItemScrollToEnd) {
        if (isItemScrollToEnd) {
            viewModel.loadMoreItems()
        }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize()
    ) {
        items(photoList) { photo ->
            PhotoListItem(photoItem = photo)
        }
    }
}

@Composable
fun PhotoListItem(photoItem: PhotoItem) {
    Row(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            modifier = Modifier
                .size(width = 100.dp, height = 80.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = photoItem.url,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = photoItem.title, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = photoItem.description, fontSize = 14.sp)
        }
    }
}