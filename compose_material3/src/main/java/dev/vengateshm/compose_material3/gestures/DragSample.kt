package dev.vengateshm.compose_material3.gestures

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun DragSample() {
    var offsetX by remember {
        mutableFloatStateOf(0f)
    }
    var offsetY by remember {
        mutableFloatStateOf(0f)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .offset {
                IntOffset(
                    x = offsetX.toInt(),
                    y = offsetY.toInt()
                )
            }
            .background(color = Color.Magenta)
            .size(100.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            })
    }
}