package dev.vengateshm.android_kotlin_compose_practice.modifiers

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

enum class DragValue { Start, Center, End }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnchoredDraggableSample() {
    val width = 96.dp
    val squareSize = 48.dp

    val density = LocalDensity.current
    val anchors =
        with(density) {
            DraggableAnchors {
                DragValue.Start at 0f
                DragValue.Center at 24.dp.toPx()
                DragValue.End at 96.dp.toPx()
            }
        }

    val state =
        remember {
            AnchoredDraggableState(
                initialValue = DragValue.Start,
                anchors = anchors,
                positionalThreshold = { distance ->
                    distance * 0.5f
                },
                velocityThreshold = {
                    with(density) { 48.dp.toPx() }
                },
                animationSpec = tween(),
            )
        }

    Box(
        modifier =
            Modifier
                .width(width)
                .anchoredDraggable(
                    state = state,
                    orientation = Orientation.Horizontal,
                ),
    ) {
        Box(
            Modifier
                .offset { IntOffset(state.offset.roundToInt(), 0) }
                .size(squareSize)
                .background(color = Color.LightGray),
        )
    }
}
