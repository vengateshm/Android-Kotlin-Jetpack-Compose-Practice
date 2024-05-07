package dev.vengateshm.compose_material3.custom_gestures

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
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
fun DragAfterLongPressSample(modifier: Modifier = Modifier) {
    var dragAfterLongPressCount by remember { mutableIntStateOf(0) }
    var dragAfterLongPressText by remember { mutableStateOf("DragAfterLongPress:") }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = dragAfterLongPressText)
        Box(modifier = Modifier
            .size(100.dp)
            .background(color = Color.Red)
            .pointerInput(Unit) {
                detectDragGesturesAfterLongPress { _, _ ->
                    dragAfterLongPressCount += 1
                    dragAfterLongPressText = "DragAfterLongPress $dragAfterLongPressCount"
                }
            })
    }
}