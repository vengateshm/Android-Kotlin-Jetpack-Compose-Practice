package dev.vengateshm.compose_material3.custom_ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

enum class HorizontalDragState {
    Idle,
    StartToEnd,
    EndToStart
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyListSwipeToReveal(modifier: Modifier = Modifier) {

    val menuList = remember { mutableStateListOf("Pizza", "Burger", "Crushers", "Diet Coke") }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = menuList, key = { name -> name }) { menu ->
            var boxSize by remember { mutableFloatStateOf(0f) }
            val scope = rememberCoroutineScope()
            val anchors = DraggableAnchors {
                HorizontalDragState.Idle at 0f
                HorizontalDragState.StartToEnd at boxSize / 3
                HorizontalDragState.EndToStart at -boxSize / 3
            }
            val anchoredState = remember {
                AnchoredDraggableState(
                    initialValue = HorizontalDragState.Idle,
                    anchors = anchors,
                    positionalThreshold = { distance: Float -> distance * 0.3f },
                    velocityThreshold = { 0.3f },
                    snapAnimationSpec = tween(),
                    decayAnimationSpec = exponentialDecay()
                )
            }

            SideEffect {
                anchoredState.updateAnchors(anchors)
            }

            val iconsBackgroundColor by animateColorAsState(
                targetValue =
                when (anchoredState.targetValue) {
                    HorizontalDragState.Idle -> Color.DarkGray
                    HorizontalDragState.StartToEnd -> Color.Green
                    HorizontalDragState.EndToStart -> Color.Red
                }, label = "animate color"
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItem()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .height(70.dp)
                        .background(color = iconsBackgroundColor)
                        .border(2.dp, Color.White)
                        .align(alignment = Alignment.CenterStart),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = {
                        scope.launch {
                            anchoredState.animateTo(HorizontalDragState.Idle)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite icon",
                            tint = Color.White
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .height(70.dp)
                        .background(color = iconsBackgroundColor)
                        .border(2.dp, Color.White)
                        .align(alignment = Alignment.CenterEnd),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = {
                        menuList.remove(menu)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete icon",
                            tint = Color.White
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            boxSize = size.width
                        }
                        .offset {
                            IntOffset(
                                x = anchoredState
                                    .requireOffset()
                                    .roundToInt(), y = 0
                            )
                        }
                        .fillMaxWidth()
                        .height(70.dp)
                        .anchoredDraggable(
                            state = anchoredState,
                            orientation = Orientation.Horizontal
                        )
                        .background(Color.LightGray)
                        .border(2.dp, Color.White)
                ) {
                    Text(text = menu, fontSize = 24.sp, modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}