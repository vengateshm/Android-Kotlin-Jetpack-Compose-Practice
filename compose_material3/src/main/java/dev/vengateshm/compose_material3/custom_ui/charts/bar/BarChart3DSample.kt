package dev.vengateshm.compose_material3.custom_ui.charts.bar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

data class BarChartInput(
    val value: Int,
    val description: String,
    val color: Color
)

val gray = Color(0XFF3F3F3F)
val orange = Color(0XFFdb660d)
val brightBlue = Color(0XFF027cf5)
val green = Color(0XFF0ddb25)
val purple = Color(0XFF9b11ba)
val blueGray = Color(0XFF404352)
val redOrange = Color(0XFFe84a23)
val darkGray = Color(0XFF1a1717)
val white = Color(0XFFF3F3F3)

@Composable
fun BarChart3DSample(modifier: Modifier = Modifier) {
    var showDescription by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.7f))
            .padding(24.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Preferred Programming Languages",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            BarChart3D(
                modifier = modifier.fillMaxWidth(),
                list = listOf(
                    BarChartInput(value = 28, description = "Kotlin", color = orange),
                    BarChartInput(value = 15, description = "Swift", color = brightBlue),
                    BarChartInput(value = 11, description = "Ruby", color = green),
                    BarChartInput(value = 7, description = "Cobol", color = purple),
                    BarChartInput(value = 14, description = "C++", color = blueGray),
                    BarChartInput(value = 9, description = "C", color = redOrange),
                    BarChartInput(value = 21, description = "Python", color = darkGray),
                ),
                showDescription = showDescription
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Show description",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.width(width = 8.dp))
                Switch(
                    checked = showDescription,
                    onCheckedChange = {
                        showDescription = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = orange,
                        uncheckedThumbColor = white,
                    )
                )
            }
        }
    }
}

@Composable
fun BarChart3D(
    modifier: Modifier = Modifier,
    list: List<BarChartInput>,
    showDescription: Boolean
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val sum by remember {
            mutableIntStateOf(list.sumOf { it.value })
        }
        list.forEach { input ->
            val percentage = input.value / sum.toFloat()
            Bar3D(
                modifier = Modifier
                    .height(120.dp * percentage * list.size)
                    .width(40.dp),
                primaryColor = input.color,
                percentage = percentage,
                description = input.description,
                showDescription = showDescription
            )
        }
    }
}

@Composable
fun Bar3D(
    modifier: Modifier = Modifier,
    primaryColor: Color,
    percentage: Float,
    description: String,
    showDescription: Boolean
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val width = size.width
            val height = size.height
            val barWidth = width / 5 * 3
            val barHeight = height / 8 * 7
            val barHeight3DPart = height - barHeight
            val barWidth3DPart = (width - barWidth) * (height * 0.002f)

            var path = Path().apply {
                moveTo(x = 0f, y = height)
                lineTo(x = barWidth, y = height)
                lineTo(x = barWidth, y = height - barHeight)
                lineTo(x = 0f, y = height - barHeight)
            }
            drawPath(
                path = path,
                brush = Brush.linearGradient(
                    colors = listOf(gray, primaryColor)
                )
            )
            path = Path().apply {
                moveTo(x = barWidth, y = height - barHeight)
                lineTo(x = barWidth3DPart + barWidth, y = 0f)
                lineTo(x = barWidth3DPart + barWidth, y = barHeight)
                lineTo(x = barWidth, y = height)
                close()
            }
            drawPath(
                path = path,
                brush = Brush.linearGradient(
                    colors = listOf(primaryColor, gray)
                )
            )
            path = Path().apply {
                moveTo(x = 0f, y = barHeight3DPart)
                lineTo(x = barWidth, y = barHeight3DPart)
                lineTo(x = barWidth + barWidth3DPart, y = 0f)
                lineTo(x = barWidth3DPart, y = 0f)
                close()
            }
            drawPath(
                path = path,
                brush = Brush.linearGradient(
                    colors = listOf(gray, orange)
                )
            )

            drawContext.canvas.nativeCanvas.apply {
                this.drawText(
                    "${(percentage * 100).roundToInt()}%",
                    barWidth / 5f,
                    height + 55f,
                    android.graphics.Paint().apply {
                        color = white.toArgb()
                        textSize = 11.sp.toPx()
                        isFakeBoldText = true
                    }
                )
            }
            if (showDescription) {
                rotate(
                    degrees = -50f,
                    pivot = Offset(x = barWidth3DPart - 20, y = 0f),
                ) {
                    drawContext.canvas.nativeCanvas.apply {
                        this.drawText(
                            description,
                            0f,
                            0f,
                            android.graphics.Paint().apply {
                                color = white.toArgb()
                                textSize = 14.sp.toPx()
                                isFakeBoldText = true
                            }
                        )
                    }
                }
            }
        }
    }
}