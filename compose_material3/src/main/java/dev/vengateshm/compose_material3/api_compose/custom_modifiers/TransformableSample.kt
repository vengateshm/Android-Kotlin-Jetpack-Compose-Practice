package dev.vengateshm.compose_material3.api_compose.custom_modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun TransformableSample(modifier: Modifier = Modifier) {
    var scale by remember { mutableFloatStateOf(1f) }
    var rotate by remember { mutableFloatStateOf(45f) }
    val state =
        rememberTransformableState { zoomChange: Float, panChange: Offset, rotationChange: Float ->
            scale *= zoomChange
            rotate += rotationChange
        }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                rotationZ = rotate
            }
            .transformable(state)
            .size(100.dp)
            .background(color = Color.Blue)
        )
    }
}