package dev.vengateshm.compose_material3.custom_gestures

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
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
fun FreeDragGestureSample(modifier: Modifier = Modifier) {
    var freeCount by remember { mutableIntStateOf(0) }
    var freeText by remember { mutableStateOf("Free:") }

    Column(modifier = Modifier.fillMaxSize()) {
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
    }
}