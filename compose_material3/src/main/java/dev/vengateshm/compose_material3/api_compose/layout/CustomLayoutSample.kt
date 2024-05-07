package dev.vengateshm.compose_material3.api_compose.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        content = {
            content()
        },
        measurePolicy = { measurables, constraints ->
            val placeables = measurables.map {
                it.measure(constraints)
            }
            var y = 0
            layout(constraints.maxWidth, constraints.maxHeight) {
                placeables.forEach { placeable ->
                    placeable.place(0, y)
                    y += 20.dp.roundToPx()
                }
            }
        },
        modifier = modifier
    )
}

@Composable
fun CustomLayoutSample(modifier: Modifier = Modifier) {
    CustomLayout(
        modifier = Modifier
            .background(color = Color.LightGray)
    ) {
        Text(
            text = "Jetpack",
            modifier = Modifier.height(20.dp)
        )
        Text(
            text = "Compose",
            modifier = Modifier.height(20.dp)
        )
        Text(
            text = "Custom",
            modifier = Modifier.height(20.dp)
        )
        Text(
            text = "Layout",
            modifier = Modifier.height(20.dp)
        )
    }
}

@Preview
@Composable
private fun CustomLayoutSamplePreview() {
    CustomLayoutSample()
}