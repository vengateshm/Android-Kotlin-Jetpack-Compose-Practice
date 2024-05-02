package dev.vengateshm.compose_material3.custom_gestures

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun CustomGesturesSample(modifier: Modifier = Modifier) {
    var horizontalCount by remember { mutableIntStateOf(0) }
    var horizontalText by remember { mutableStateOf("Horizontal:") }

    var verticalCount by remember { mutableIntStateOf(0) }
    var verticalText by remember { mutableStateOf("Vertical:") }

    var freeCount by remember { mutableIntStateOf(0) }
    var freeText by remember { mutableStateOf("Free:") }

    var tapCount by remember { mutableIntStateOf(0) }
    var tapText by remember { mutableStateOf("Tap:") }

    var dragAfterLongPressCount by remember { mutableIntStateOf(0) }
    var dragAfterLongPressText by remember { mutableStateOf("DragAfterLongPress:") }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = horizontalText)
        Box(modifier = Modifier
            .size(100.dp)
            .background(color = Color.Red)
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart = {
                        horizontalCount = 0
                    },
                    onDragCancel = {

                    },
                    onDragEnd = {

                    }) { _, _ ->
                    horizontalCount += 1
                    horizontalText =
                        if (horizontalCount > 15) "Horizontal : Valid" else "Horizontal : Invalid"
                }
            })
        Text(text = verticalText)
        Box(modifier = Modifier
            .size(100.dp)
            .background(color = Color.Gray)
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onDragStart = {
                        verticalText = "Vertical: Start"
                    },
                    onDragCancel = {
                        verticalText += " Cancel"
                    },
                    onDragEnd = {
                        verticalText += " End"
                    }) { change, dragAmount ->
                    if (change.position.x in 100.0..150.0 && change.position.y in 10.0..20.0) {
                        verticalCount += 1
                        verticalText = "Consumed ${verticalCount - dragAmount}"
                    }
                }
            })
        Text(text = freeText)
        Box(modifier = Modifier
            .size(100.dp)
            .background(color = Color.Red)
            .pointerInput(Unit) {
                awaitEachGesture {
                    while (true) {
                        val event = awaitPointerEvent()
                        if (event.changes.any { it.isConsumed }) {
                            return@awaitEachGesture
                        } else {
                            freeCount += 1
                            freeText = "Free $freeCount"
                        }
                    }
                }
            })
        Text(text = tapText)
        Box(modifier = Modifier
            .size(100.dp)
            .background(color = Color.Gray)
            .pointerInput(Unit) {
                detectTapGestures {
                    tapCount += 1
                    tapText = "Tap $tapCount"
                }
            })
        Text(text = dragAfterLongPressText)
        Box(modifier = Modifier
            .size(100.dp)
            .background(color = Color.Red)
            .pointerInput(Unit) {
                detectDragGesturesAfterLongPress { change, dragAmount ->
                    dragAfterLongPressCount += 1
                    dragAfterLongPressText = "DragAfterLongPress $dragAfterLongPressCount"
                }
            })
    }
}