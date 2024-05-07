package dev.vengateshm.compose_material3.api_compose.custom_modifiers

import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.R
import kotlin.math.roundToInt

enum class DraggableValue {
    Start,
    End
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalDraggableSample(modifier: Modifier = Modifier) {
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current

    val draggableAnchors = with(density) {
        DraggableAnchors {
            DraggableValue.Start at 0.dp.toPx()
            DraggableValue.End at (configuration.screenWidthDp.dp.toPx() - 80.dp.toPx())
        }
    }

    val anchorDraggableState = remember {
        AnchoredDraggableState(
            initialValue = DraggableValue.Start,
            anchors = draggableAnchors,
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { with(density) { 100.dp.toPx() } },
            snapAnimationSpec = spring(),
            decayAnimationSpec = exponentialDecay()
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(80.dp)
                .offset {
                    IntOffset(
                        x = anchorDraggableState
                            .requireOffset()
                            .roundToInt(),
                        y = 0
                    )
                }
                .anchoredDraggable(anchorDraggableState, Orientation.Horizontal),
            painter = painterResource(id = R.drawable.cmaterial3_someone_else),
            contentDescription = "Draggable Image"
        )
    }
}