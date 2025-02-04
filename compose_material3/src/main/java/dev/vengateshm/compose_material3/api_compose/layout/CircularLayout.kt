package dev.vengateshm.compose_material3.api_compose.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp

@Composable
fun CircularLayout(
    modifier: Modifier = Modifier,
    radius: Dp = 100.dp,
    startAngle: Float = 0f,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    Layout(content = content, modifier = modifier) { measurables, constraints ->
        val radiusPx = with(density) { radius.toPx() }

        val placeables = measurables.map { measurable ->
            measurable.measure(
                constraints.copy(
                    minWidth = 0,
                    minHeight = 0,
                ),
            ) // To avoid taking up max size
        }

        val maxChildWidth = placeables.maxOf { it.width }
        val maxChildHeight = placeables.maxOf { it.height }

        val desiredWidth = 2.times(radiusPx).plus(maxChildWidth).toInt()
        val desiredHeight = 2.times(radiusPx).plus(maxChildHeight).toInt()

        val layoutWidth = constraints.constrainWidth(desiredWidth)
        val layoutHeight = constraints.constrainHeight(desiredHeight)

        val centerX = layoutWidth / 2
        val centerY = layoutHeight / 2

        val angleIncrement = 360f / placeables.size

        layout(width = layoutWidth, height = layoutHeight) {
            placeables.forEachIndexed { index, placeable ->
                val angle = startAngle + (angleIncrement * index)
                val radians = Math.toRadians(angle.toDouble())
                val x = centerX + radiusPx * kotlin.math.cos(radians)
                    .toFloat() - placeable.width.div(2)// Centered
                val y = centerY + radiusPx * kotlin.math.sin(radians)
                    .toFloat() - placeable.height.div(2)
                placeable.placeRelative(x = x.toInt(), y = y.toInt())
            }
        }
    }
}

@Preview
@Composable
fun CircularLayoutPreview(modifier: Modifier = Modifier) {
    CircularLayout(
        modifier = Modifier.padding(16.dp),
        startAngle = 300f,
    ) {
        repeat(12) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "${it + 1}")
            }
        }
    }
}