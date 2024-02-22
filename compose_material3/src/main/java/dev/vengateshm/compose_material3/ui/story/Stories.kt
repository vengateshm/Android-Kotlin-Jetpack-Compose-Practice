package dev.vengateshm.compose_material3.ui.story

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun Stories(listOfImage: List<Int>) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0.0f,
        pageCount = {
            listOfImage.size
        })
    val coroutineScope = rememberCoroutineScope()

    var currentPage by remember {
        mutableIntStateOf(0)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        StoryImage(
            pagerState = pagerState,
            listOfImage = listOfImage
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = Brush.verticalGradient(listOf(Color.Black, Color.Transparent))),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.padding(4.dp))
            for (index in listOfImage.indices) {
                LinearIndicator(
                    modifier = Modifier.weight(1f),
                    index == currentPage,
                    onAnimationEnd = {
                        coroutineScope.launch {
                            if (currentPage < listOfImage.size - 1) {
                                currentPage += 1
                            }
                            pagerState.animateScrollToPage(currentPage,
                                animationSpec = tween(durationMillis = 500)
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(4.dp))
            }
        }
    }
}