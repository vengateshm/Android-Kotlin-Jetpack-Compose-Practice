package dev.vengateshm.compose_material3.custom_ui

import android.graphics.ComposePathEffect
import android.graphics.CornerPathEffect
import android.graphics.DiscretePathEffect
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt
import kotlin.random.Random

@Composable
fun SweetDonut(modifier: Modifier = Modifier) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        val baseColor = "#eb768b".toColorInt()
        val icingColor = "#ffdecd".toColorInt()

        val path = Path()
        path.addOval(oval = Rect(center = center, radius = size.width / 2.5f))
        drawPath(path = path, color = Color(0XFFEB768B))

        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                center.x,
                center.y,
                (size.width / 2.5f) - 20,
                Paint().apply {
                    color = icingColor
                    pathEffect = ComposePathEffect(
                        CornerPathEffect(100f),
                        DiscretePathEffect(60f, 15f)
                    )
                }
            )
        }

        for (x in 1..100) {
            val angle = Random.nextInt(360).toFloat()
            val position = Random.nextInt(-1000, 1000)
            clipPath(path) {
                drawArc(
                    color = Color(Random.nextLong(0xff000000, 0xffffffff)),
                    startAngle = angle,
                    sweepAngle = Random.nextInt(3, 6).toFloat(),
                    useCenter = false,
                    style = Stroke(
                        width = Random.nextInt(3, 8).toFloat(),
                        cap = StrokeCap.Round
                    ),
                    topLeft = Offset(x = center.x - position, center.y - position),
                    size = Size(
                        width = size.width / 2,
                        height = size.height / 2
                    )
                )
            }
        }

        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                center.x,
                center.y,
                100f,
                Paint().apply {
                    color = android.graphics.Color.WHITE
                    setShadowLayer(100f, 0f, -30f, baseColor)
                }
            )
        }
    }
}

@Preview
@Composable
private fun SweetDonutPreview() {
    SweetDonut()
}