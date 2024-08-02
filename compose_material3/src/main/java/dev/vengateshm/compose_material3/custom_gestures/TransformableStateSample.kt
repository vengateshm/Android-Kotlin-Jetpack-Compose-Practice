package dev.vengateshm.compose_material3.custom_gestures

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateRotateBy
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun TransformableStateSample(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ReverseFlip()
        Spacer(modifier = Modifier.height(16.dp))
        BoxedFlip()
    }
}

@Composable
fun ReverseFlip(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    var rotationY by remember { mutableFloatStateOf(0f) }
    val transformableState = rememberTransformableState { _, _, rotationChange ->
        rotationY += rotationChange
    }
    Box(modifier = Modifier
        .graphicsLayer {
            this.rotationY = rotationY
        }
        .transformable(transformableState)
        .pointerInput(Unit) {
            detectTapGestures(
                onTap = {
                    scope.launch { transformableState.animateRotateBy(180f) }
                },
                onDoubleTap = {
                    scope.launch { transformableState.animateRotateBy(360f) }
                }
            )
        }
        .size(100.dp)
        .background(color = Color.DarkGray),
        contentAlignment = Alignment.Center) {
        Text(text = "REVERSE", color = Color.White)
    }
}

@Composable
fun BoxedFlip(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    var rotationY by remember { mutableFloatStateOf(0f) }
    val transformableState = rememberTransformableState { _, _, rotationChange ->
        rotationY += rotationChange
    }
    Box(modifier = Modifier
        .graphicsLayer {
            this.rotationY = rotationY
        }
        .transformable(transformableState)
        .pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                when {
                    rotationY < -75 -> scope.launch { transformableState.animateRotateBy(75f) }
                    rotationY < 75 -> scope.launch { transformableState.animateRotateBy(-75f) }
                    else -> rotationY = dragAmount.x
                }
            }
        }
        .size(100.dp)
        .background(color = Color.DarkGray),
        contentAlignment = Alignment.Center) {
        Text(text = "BOXED", color = Color.White)
    }
}