package dev.vengateshm.android_kotlin_compose_practice.pie_chart

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.PI
import kotlin.math.atan2

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    outerCircleRadius: Float = 500f,
    innerCircleRadius: Float = 250f,
    transparentWidth: Float = 70f,
    input: List<PieChartData>,
    centerText: String = "",
) {
    var circleCenter by remember {
        mutableStateOf(Offset.Zero)
    }

    var inputList by remember {
        mutableStateOf(input)
    }

    var isCenterTapped by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier =
                Modifier
                    .fillMaxSize()
                    .pointerInput(true) {
                        detectTapGestures(
                            onTap = { offset ->
                                val tapAngleInDegrees =
                                    (
                                        -atan2(
                                            x = circleCenter.y - offset.y,
                                            y = circleCenter.x - offset.x,
                                        ) * (180f / PI).toFloat() - 90f
                                    ).mod(360f)
                                val centerClicked =
                                    if (tapAngleInDegrees < 90) {
                                        offset.x < circleCenter.x + innerCircleRadius && offset.y < circleCenter.y + innerCircleRadius
                                    } else if (tapAngleInDegrees < 180) {
                                        offset.x > circleCenter.x - innerCircleRadius && offset.y < circleCenter.y + innerCircleRadius
                                    } else if (tapAngleInDegrees < 270) {
                                        offset.x > circleCenter.x - innerCircleRadius && offset.y > circleCenter.y - innerCircleRadius
                                    } else {
                                        offset.x < circleCenter.x + innerCircleRadius && offset.y > circleCenter.y - innerCircleRadius
                                    }
                                if (centerClicked) {
                                    inputList = inputList.map { it.copy(isTapped = !isCenterTapped) }
                                    isCenterTapped = !isCenterTapped
                                } else {
                                    val anglePerValue = 360f / input.sumOf { it.value }
                                    var curAngle = 0f
                                    inputList.forEach { pieChartData ->
                                        curAngle += pieChartData.value * anglePerValue
                                        if (tapAngleInDegrees < curAngle) {
                                            val description = pieChartData.description
                                            inputList =
                                                inputList.map {
                                                    if (description == it.description) {
                                                        it.copy(isTapped = !it.isTapped)
                                                    } else {
                                                        it.copy(isTapped = false)
                                                    }
                                                }
                                            return@detectTapGestures
                                        }
                                    }
                                }
                            },
                        )
                    },
        ) {
            val width = size.width
            val height = size.height
            circleCenter = Offset(width / 2f, height / 2f)

            val totalValue = input.sumOf { it.value }
            val anglePerValue = 360f / totalValue

            var currentStartAngle = 0f
            inputList.forEach { pieChartData ->
                val scale = if (pieChartData.isTapped) 1.1f else 1.0f
                val angleToDraw = pieChartData.value * anglePerValue
                scale(scale) {
                    drawArc(
                        color = pieChartData.color,
                        startAngle = currentStartAngle,
                        sweepAngle = angleToDraw,
                        useCenter = true,
                        size =
                            Size(
                                width = outerCircleRadius * 2f,
                                height = outerCircleRadius * 2f,
                            ),
                        topLeft =
                            Offset(
                                x = (width - outerCircleRadius * 2f) / 2f,
                                y = (height - outerCircleRadius * 2f) / 2f,
                            ),
                    )
                    currentStartAngle += angleToDraw
                }

                var rotateAngle = currentStartAngle - angleToDraw / 2f - 90f
                var factor = 1f
                if (rotateAngle > 90f) {
                    rotateAngle = (rotateAngle + 180).mod(360f)
                    factor = -0.92f
                }

                val percentage = (pieChartData.value / totalValue.toFloat() * 100).toInt()

                drawContext.canvas.nativeCanvas.apply {
                    if (percentage > 3) {
                        rotate(rotateAngle) {
                            drawText(
                                "$percentage %",
                                circleCenter.x,
                                circleCenter.y + (outerCircleRadius - (outerCircleRadius - innerCircleRadius) / 2f) * factor,
                                Paint().apply {
                                    textSize = 13.sp.toPx()
                                    textAlign = Paint.Align.CENTER
                                    color = Color.White.toArgb()
                                },
                            )
                        }
                    }
                }

                if (pieChartData.isTapped) {
                    val tabRotation = currentStartAngle - angleToDraw - 90f
                    rotate(tabRotation) {
                        drawRoundRect(
                            topLeft = circleCenter,
                            size = Size(12f, outerCircleRadius * 1.2f),
                            color = Color.Gray,
                            cornerRadius = CornerRadius(15f, 15f),
                        )
                    }
                    rotate(tabRotation + angleToDraw) {
                        drawRoundRect(
                            topLeft = circleCenter,
                            size = Size(12f, outerCircleRadius * 1.2f),
                            color = Color.Gray,
                            cornerRadius = CornerRadius(15f, 15f),
                        )
                    }
                    rotate(rotateAngle) {
                        drawContext.canvas.nativeCanvas.apply {
                            drawText(
                                "${pieChartData.description}: ${pieChartData.value}",
                                circleCenter.x,
                                circleCenter.y + outerCircleRadius * 1.3f * factor,
                                Paint().apply {
                                    textSize = 22.sp.toPx()
                                    textAlign = Paint.Align.CENTER
                                    color = Color.White.toArgb()
                                    isFakeBoldText = true
                                },
                            )
                        }
                    }
                }
            }

            if (inputList.first().isTapped) {
                rotate(-90f) {
                    drawRoundRect(
                        topLeft = circleCenter,
                        size = Size(12f, outerCircleRadius * 1.2f),
                        color = Color.Gray,
                        cornerRadius = CornerRadius(15f, 15f),
                    )
                }
            }

            drawContext.canvas.nativeCanvas.apply {
                drawCircle(
                    circleCenter.x,
                    circleCenter.y,
                    innerCircleRadius,
                    Paint().apply {
                        color = Color.White.copy(alpha = 0.6f).toArgb()
                        setShadowLayer(10f, 0f, 0f, Color.Gray.toArgb())
                    },
                )
            }

            drawCircle(
                color = Color.White.copy(alpha = 0.2f),
                radius = innerCircleRadius + (transparentWidth / 2f),
            )
        }

        Text(
            modifier =
                Modifier
                    .width(Dp(innerCircleRadius / 1.5f))
                    .padding(25.dp),
            text = centerText,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        )
    }
}

data class PieChartData(
    val color: Color,
    val value: Int,
    val description: String,
    val isTapped: Boolean,
)

@Preview(showBackground = true)
@Composable
fun PieChartPreview() {
    PieChart(input = pieChartDataList)
}

val pieChartDataList =
    listOf(
        PieChartData(
            color = Color.Black,
            value = 30,
            description = "C#",
            isTapped = false,
        ),
        PieChartData(
            color = Color.Blue,
            value = 50,
            description = "Rust",
            isTapped = false,
        ),
        PieChartData(
            color = Color.Cyan,
            value = 12,
            description = "Kotlin",
            isTapped = false,
        ),
        PieChartData(
            color = Color.Green,
            value = 50,
            description = "Java",
            isTapped = false,
        ),
        PieChartData(
            color = Color.Yellow,
            value = 8,
            description = "Ruby",
            isTapped = false,
        ),
    )
