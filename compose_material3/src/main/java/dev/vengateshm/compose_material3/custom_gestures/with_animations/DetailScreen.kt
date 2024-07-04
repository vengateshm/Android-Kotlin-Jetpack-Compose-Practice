package dev.vengateshm.compose_material3.custom_gestures.with_animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(modifier: Modifier = Modifier, colorItem: ColorItem) {
    val coroutineScope = rememberCoroutineScope()
    val translationY = remember { Animatable(0f) }
    val draggableState = rememberDraggableState { dragAmount ->
        coroutineScope.launch {
            translationY.snapTo(translationY.value + dragAmount)
        }
    }
    val decay = rememberSplineBasedDecay<Float>()
    val configuration = LocalConfiguration.current
    val screenHeight = with(LocalDensity.current) { configuration.screenHeightDp.toDp().toPx() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .graphicsLayer {
                this.translationY = translationY.value
                val scale = lerp(1f, 0.3f, translationY.value / screenHeight)
                this.scaleX = scale
                this.scaleY = scale
            }
            .draggable(
                state = draggableState,
                orientation = Orientation.Vertical,
                onDragStopped = { velocity: Float ->
                    val decayY = decay.calculateTargetValue(translationY.value, velocity)
                    coroutineScope.launch {
                        val targetY = if (decayY > screenHeight * 0.5) screenHeight else 0f
                        val canReachTargetWithDecay = (decayY > targetY && targetY == screenHeight)
                                || (decayY < targetY && targetY == 0f)
                        if (canReachTargetWithDecay) {
                            translationY.animateDecay(
                                animationSpec = decay,
                                initialVelocity = velocity
                            )
                        } else {
                            translationY.animateTo(targetY, initialVelocity = velocity)
                        }
                    }
                })
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(colorItem.color))
        )
        Text(text = colorItem.name)
    }
}