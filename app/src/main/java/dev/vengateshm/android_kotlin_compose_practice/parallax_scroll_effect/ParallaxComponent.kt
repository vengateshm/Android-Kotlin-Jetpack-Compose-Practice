package dev.vengateshm.android_kotlin_compose_practice.parallax_scroll_effect

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.vengateshm.android_kotlin_compose_practice.core.toDp

@Composable
fun ParallaxComponent() {
    val moonScrollSpeed = 0.08f
    val midBgScrollSpeed = 0.03f

    val imageHeight = (LocalConfiguration.current.screenWidthDp * (2f / 3f)).dp
    val lazyListState = rememberLazyListState()

    var moonScrollOffset by remember {
        mutableStateOf(0f)
    }
    var midBgScrollOffset by remember {
        mutableStateOf(0f)
    }

    val nestedScrollConnection = object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            val delta = available.y

            val layoutInfo = lazyListState.layoutInfo
            // Check if first item is visible
            if (lazyListState.firstVisibleItemIndex == 0)
                return Offset.Zero
            if (layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1)
                return Offset.Zero
            moonScrollOffset += delta * moonScrollSpeed
            midBgScrollOffset += delta * midBgScrollSpeed
            return Offset.Zero // Whatever the user scrolls with his finger
        }
    }

    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .fillMaxWidth()
            .nestedScroll(nestedScrollConnection),
        content = {
            items(10) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = "Sample Item"
                )
            }
            item {
                Box(
                    modifier = Modifier
                        .clipToBounds()
                        .fillMaxWidth()
                        .height(imageHeight + midBgScrollOffset.toDp())
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFFf36b21),
                                    Color(0XFFf9a521)
                                )
                            )
                        )
                ) {
                    Image(
                        painter = painterResource(id = dev.vengateshm.android_kotlin_compose_practice.R.drawable.ic_moonbg),
                        contentDescription = "moon",
                        contentScale = ContentScale.FillWidth,
                        alignment = Alignment.BottomCenter,
                        modifier = Modifier
                            .matchParentSize()
                            .graphicsLayer {
                                translationY = moonScrollOffset
                            })
                    Image(
                        painter = painterResource(id = dev.vengateshm.android_kotlin_compose_practice.R.drawable.ic_midbg),
                        contentDescription = "mid bg",
                        contentScale = ContentScale.FillWidth,
                        alignment = Alignment.BottomCenter,
                        modifier = Modifier
                            .matchParentSize()
                            .graphicsLayer {
                                translationY = midBgScrollOffset
                            })
                    Image(
                        painter = painterResource(id = dev.vengateshm.android_kotlin_compose_practice.R.drawable.ic_outerbg),
                        contentDescription = "outer bg",
                        contentScale = ContentScale.FillWidth,
                        alignment = Alignment.BottomCenter,
                        modifier = Modifier.matchParentSize()
                    )
                }
            }
            items(20) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = "Sample Item"
                )
            }
        })
}