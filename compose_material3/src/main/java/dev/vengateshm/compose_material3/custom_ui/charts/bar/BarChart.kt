package dev.vengateshm.compose_material3.custom_ui.charts.bar

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

data class InfoGraph(
    val number: Int,
    val year: Int
)

@Composable
fun BarChart(modifier: Modifier = Modifier) {
    val data = listOf(
        InfoGraph(number = 80, year = 2017),
        InfoGraph(number = 310, year = 2018),
        InfoGraph(number = 330, year = 2019),
        InfoGraph(number = 160, year = 2020),
        InfoGraph(number = 280, year = 2021),
        InfoGraph(number = 100, year = 2022),
        InfoGraph(number = 350, year = 2023)
    )

    var size by remember { mutableStateOf(IntSize.Zero) }

    Column(
        modifier = Modifier
            .background(Color.LightGray)
    ) {
        val max = data.maxOfOrNull { it.number.dp }?.value
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .onSizeChanged {
                    size = it
                },
            verticalAlignment = Alignment.Bottom
        ) {
            data.forEach {
                Bar(
                    barHeight = it.number.dp,
                    max = max ?: 0f,
                    onBarClick = {

                    }
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            data.forEach {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it.year.toString(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun RowScope.Bar(
    modifier: Modifier = Modifier,
    barHeight: Dp,
    max: Float,
    onBarClick: () -> Unit
) {

    var height by remember { mutableStateOf(0.dp) }
    val animatedHeight by animateDpAsState(
        targetValue = height,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "bar height animation"
    )

    LaunchedEffect(key1 = Unit) {
        delay(300.milliseconds)
        height = barHeight
    }

    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .padding(top = 4.dp)
            .height(animatedHeight)
            .weight(weight = 1f)
            .border(BorderStroke(width = 1.dp, color = Color(0XFF333333)))
            .background(color = Color.Red.copy(alpha = barHeight.value / max))
            .clickable {
                onBarClick()
            }
    )
}