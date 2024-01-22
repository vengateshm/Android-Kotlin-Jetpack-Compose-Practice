package dev.vengateshm.android_kotlin_compose_practice.drag_and_drop

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun DragAndDropDemo() {
    var offset by remember { mutableStateOf(Offset.Zero) }
    var backgroundColor by remember { mutableStateOf(Color.Gray) }

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(color = backgroundColor)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consumeAllChanges()
                        println("OFFSET $offset")
                        offset += dragAmount
                        println("OFFSET $offset")
                    }
                }
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            backgroundColor = Color.Green
                        },
                    )
                }
                .offset(x = offset.x.dp, y = offset.y.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Drag me!",
            modifier = Modifier.padding(24.dp),
        )
    }
}
