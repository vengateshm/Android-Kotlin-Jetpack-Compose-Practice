package dev.vengateshm.compose_material3.custom_gestures

import androidx.compose.foundation.background
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
fun VerticalDragGestureSample(modifier: Modifier = Modifier) {
    var verticalCount by remember { mutableIntStateOf(0) }
    var verticalText by remember { mutableStateOf("Vertical:") }

    Column(modifier = Modifier.fillMaxSize()) {
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
    }
}