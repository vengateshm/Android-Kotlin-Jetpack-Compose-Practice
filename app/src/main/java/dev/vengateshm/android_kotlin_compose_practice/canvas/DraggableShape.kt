package dev.vengateshm.android_kotlin_compose_practice.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

val touchAreaOffset = 150f

@Composable
fun DraggableShape() {
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    var circlePosition by remember {
        mutableStateOf(
            Offset(
                (displayMetrics.widthPixels / 2).toFloat(),
                (displayMetrics.heightPixels / 2).toFloat()
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                drawIntoCanvas {
                    drawCircle(
                        color = Color.Blue,
                        center = circlePosition,
                        radius = 100f
                    )
                }
            }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
//                    detectTapGestures(
//                        onDoubleTap = {
//                            circlePosition += Offset(x = 150f, y = 150f)
//                        }
//                    )
                    detectDragGestures { change, dragAmount ->
                        if (change.pressed) {  // Check if press is within circle bounds
                            val pressedOffset = change.position
                            if (pressedOffset.x in circlePosition.x - touchAreaOffset..circlePosition.x + touchAreaOffset &&
                                pressedOffset.y in circlePosition.y - touchAreaOffset..circlePosition.y + touchAreaOffset
                            ) {
                                circlePosition += dragAmount
                            }
                        }
                    }
                }
                .alpha(0f),
        )
    }
}

@Preview
@Composable
fun DraggableShapePreview() {
    DraggableShape()
}