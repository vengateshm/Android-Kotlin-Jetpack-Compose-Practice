package dev.vengateshm.android_kotlin_compose_practice.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun BarChartCanvas(
    list: List<Int>,
    barSelected: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .height(150.dp)
                .horizontalScroll(
                    state = scrollState,
                ),
    ) {
        val density = LocalDensity.current
        val horizontalPadding = with(density) { 12.dp.toPx() }
        val distance = with(density) { 26.dp.toPx() }
        val calculatedWidth =
            with(density) {
                (distance.times(list.size) + horizontalPadding.times(2)).toDp()
            }
        Canvas(
            modifier =
                Modifier
                    .fillMaxHeight()
                    .width(calculatedWidth),
        ) {
            val smallPadding = with(density) { 2.dp.toPx() }
            val lineDistance = size.height.minus(smallPadding.times(2)).div(4)
            repeat(5) {
                drawLine(
                    color = Color.Gray,
                    start = Offset(x = 0f, y = smallPadding.plus(it.times(lineDistance))),
                    end = Offset(x = size.width, y = smallPadding.plus(it.times(lineDistance))),
                )
            }
        }
    }
}
