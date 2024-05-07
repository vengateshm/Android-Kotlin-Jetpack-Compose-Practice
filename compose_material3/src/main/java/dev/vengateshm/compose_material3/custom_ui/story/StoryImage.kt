package dev.vengateshm.compose_material3.custom_ui.story

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun StoryImage(pagerState: PagerState, listOfImage: List<Int>) {
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false
    ) { page ->
        Image(
            painter = painterResource(id = listOfImage[page]),
            contentScale = ContentScale.Crop,
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
    }
}