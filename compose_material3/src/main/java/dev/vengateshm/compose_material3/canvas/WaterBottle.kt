package dev.vengateshm.compose_material3.canvas

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WaterBottleScreen() {

    var usedWaterAmount: Int by remember {
        mutableIntStateOf(400)
    }

    val totalWaterAmount = remember {
        2400
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        WaterBottle(
            modifier = Modifier
                .width(250.dp)
                .weight(1f),
            totalWaterAmount = 2400,
            usedWaterAmount = usedWaterAmount,
            unit = "ml"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Total amount is $totalWaterAmount")
        Button(onClick = { usedWaterAmount += 200 }) {
            Text(text = "Drink")
        }
    }
}

@Composable
fun WaterBottle(
    modifier: Modifier = Modifier,
    totalWaterAmount: Int = 0,
    usedWaterAmount: Int = 0,
    unit: String = "",
    waterColor: Color = Color(0xff279eff),
    bottleColor: Color = Color.White,
    capColor: Color = Color(0xff0065b9)
) {
    val waterPercent by animateFloatAsState(
        targetValue = usedWaterAmount.toFloat() / totalWaterAmount.toFloat(),
        label = "Water waves animation",
        animationSpec = tween(durationMillis = 1000)
    )

    Box(
        modifier = modifier
            .width(200.dp)
            .height(600.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            val capWidth = size.width * 0.55f
            val capHeight = size.height * 0.13f

            val bottleBodyPath = Path().apply {
                moveTo(x = width * 0.3f, y = height * 0.1f)
                lineTo(x = width * 0.3f, y = height * 0.2f)
                quadraticTo(
                    x1 = 0f, y1 = height * 0.3f,
                    x2 = 0f, y2 = height * 0.4f
                )
                lineTo(x = 0f, y = height * 0.95f)
                quadraticTo(
                    x1 = 0f, y1 = height,
                    x2 = width * 0.05f, y2 = height
                )
                lineTo(x = width * 0.95f, y = height)
                quadraticTo(
                    x1 = width, y1 = height,
                    x2 = width, y2 = height * 0.95f
                )
                lineTo(x = width, y = height * 0.4f)
                quadraticTo(
                    x1 = width, y1 = height * 0.3f,
                    x2 = width * 0.7f, y2 = height * 0.2f
                )
                lineTo(x = width * 0.7f, y = height * 0.1f)
            }

            clipPath(bottleBodyPath) {
                drawRect(color = bottleColor, size = size)

                val waterWavesPosition = (1 - waterPercent) * size.height
                val waterPath = Path().apply {
                    moveTo(x = 0f, y = waterWavesPosition)
                    lineTo(
                        x = width,
                        y = waterWavesPosition
                    )
                    lineTo(
                        x = size.width,
                        y = size.height
                    )
                    lineTo(x = 0f, y = size.height)
                    close()
                }
                drawPath(
                    path = waterPath,
                    color = waterColor
                )
            }

            drawRoundRect(
                color = capColor,
                size = Size(capWidth, capHeight),
                topLeft = Offset(size.width / 2f - capWidth / 2f, 0f),
                cornerRadius = CornerRadius(45f, 45f)
            )
        }

        val text = buildAnnotatedString {
            withStyle(
                style = if (waterPercent == 0.5f) {
                    SpanStyle(
                        brush = Brush.verticalGradient(listOf(waterColor, Color.White)),
                        fontSize = 44.sp
                    )
                } else {
                    SpanStyle(
                        color = if (waterPercent > 0.5f) bottleColor else waterColor,
                        fontSize = 44.sp
                    )
                }
            ) {
                append(usedWaterAmount.toString())
            }
            withStyle(
                style = if (waterPercent == 0.5f) {
                    SpanStyle(
                        brush = Brush.verticalGradient(listOf(waterColor, Color.White)),
                        fontSize = 44.sp
                    )
                } else {
                    SpanStyle(
                        color = if (waterPercent > 0.5f) bottleColor else waterColor,
                        fontSize = 22.sp
                    )
                }
            ) {
                append(" ")
                append(unit)
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WaterBottlePreview() {
    WaterBottle(
        totalWaterAmount = 2400,
        usedWaterAmount = 400,
    )
}