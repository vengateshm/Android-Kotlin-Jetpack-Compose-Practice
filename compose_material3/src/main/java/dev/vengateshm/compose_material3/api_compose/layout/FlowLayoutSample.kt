package dev.vengateshm.compose_material3.api_compose.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp

@Composable
fun FlowLayoutSample(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
//        FlowLayout(modifier = Modifier.fillMaxSize()) {
//            repeat(100) {
//                Box(
//                    modifier = Modifier.padding(8.dp).size(50.dp, 20.dp)
//                        .background(color = Color.LightGray)
//                )
//            }
//        }
//        StaircaseLayout(modifier = Modifier.fillMaxSize()) {
//            repeat(100) {
//                Box(
//                    modifier = Modifier.padding(8.dp).size(50.dp, 20.dp)
//                        .background(color = Color.LightGray)
//                )
//            }
//        }
        AlternateStaircaseLayout(modifier = Modifier.fillMaxSize()) {
            repeat(100) {
                Box(
                    modifier = Modifier.padding(8.dp).size(50.dp, 18.dp)
                        .background(color = Color.LightGray), contentAlignment = Alignment.Center
                ) {
                    Text(text = it.toString())
                }
            }
        }
    }
}

@Composable
fun FlowLayout(
    modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier, content = content
    ) { measurables, constraints ->
        layout(constraints.maxWidth, constraints.maxHeight) {
            val placeables = measurables.map { measurable ->
                measurable.measure(
                    constraints.copy(
                        minWidth = 0, minHeight = 0
                    )
                )
            }

            var x = 0
            var y = 0
            var maxY = 0
            placeables.forEach { placeable ->
                if (x + placeable.width > constraints.maxWidth) {
                    x = 0
                    y += maxY
                }
                placeable.place(x, y)
                x += placeable.width
                // Find max height of y so next row can be filled
                // from that y position
                if (maxY < placeable.height) {
                    maxY = placeable.height
                }
            }
        }
    }
}

@Composable
fun StaircaseLayout(
    modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier, content = content
    ) { measurables, constraints ->
        layout(constraints.maxWidth, constraints.maxHeight) {
            val placeables = measurables.map { measurable ->
                measurable.measure(
                    constraints.copy(
                        minWidth = 0, minHeight = 0
                    )
                )
            }

            var x = 0
            var y = 0
            placeables.forEach { placeable ->
                if (x + placeable.width > constraints.maxWidth) {
                    x = 0
                }
                placeable.place(x, y)
                x += placeable.width
                y += (placeable.height * 1.0).toInt()
            }
        }
    }
}

@Composable
fun AlternateStaircaseLayout(
    modifier: Modifier = Modifier, spacing: Int = 0, content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier, content = content
    ) { measurables, constraints ->
        layout(constraints.maxWidth, constraints.maxHeight) {
            val placeables = measurables.map { measurable ->
                measurable.measure(
                    constraints.copy(
                        minWidth = 0, minHeight = 0
                    )
                )
            }

            var x = 0
            var y = 0
            var direction = 1
            placeables.forEach { placeable ->
                if (direction > 0 && x + placeable.width > constraints.maxWidth) {
                    direction *= -1
                } else if (direction < 0 && x - placeable.width < 0) {
                    direction *= -1
                }
                placeable.place(x, y)
                x += placeable.width * direction
                y += placeable.height + spacing
            }
        }
    }
}