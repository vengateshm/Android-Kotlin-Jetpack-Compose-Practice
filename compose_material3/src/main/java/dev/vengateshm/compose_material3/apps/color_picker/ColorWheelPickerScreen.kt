package dev.vengateshm.compose_material3.apps.color_picker

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.*
import android.graphics.Color as AndroidColor

@Composable
fun ColorWheelPickerScreen(viewModel: ColorPickerViewModel = viewModel()) {
    val currentColor = viewModel.selectedColor
    val textColor = if (currentColor.luminance() > 0.5f) Color.Black else Color.White

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(currentColor),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            ColorInfoDisplay(currentColor, textColor)
            ColorWheelPicker(
                modifier = Modifier.size(300.dp),
                currentColor = currentColor,
                onColorSelected = { viewModel.updateColor(it) },
            )
            SaturationValueSlider(
                currentColor = currentColor,
                onColorChanged = { viewModel.updateColor(it) },
            )
        }
    }
}

@Composable
fun ColorWheelPicker(
    modifier: Modifier = Modifier,
    currentColor: Color,
    onColorSelected: (Color) -> Unit,
) {
    var center by remember { mutableStateOf(Offset.Zero) }
    var radius by remember { mutableFloatStateOf(0f) }
    val hsv = remember { FloatArray(3) }

    currentColor.toHsv(hsv)
    val (hue, saturation) = hsv.let { it[0] to it[1] }

    val hueColors = remember {
        listOf(
            Color.Red,
            Color.Yellow,
            Color.Green,
            Color.Cyan,
            Color.Blue,
            Color.Magenta,
            Color.Red,
        )
    }

    Canvas(
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    val position = change.position
                    val angle = atan2(
                        position.y - center.y,
                        position.x - center.x,
                    ).toDegrees()

                    val distance = sqrt(
                        (position.x - center.x).pow(2) +
                                (position.y - center.y).pow(2),
                    )

                    val saturation = (distance / radius).coerceIn(0f, 1f)
                    val hue = (angle + 360) % 360

                    if (distance <= radius) {
                        onColorSelected(Color.hsv(hue, saturation, 1f))
                    }
                }
            },
    ) {
        center = this.center
        radius = this.size.minDimension / 2

        drawCircle(
            brush = Brush.sweepGradient(hueColors),
            radius = radius,
            center = center,
        )

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.White, Color.Transparent),
                center = center,
                radius = radius,
            ),
            radius = radius,
            center = center,
        )

        // Calculate marker position
        val angleRadians = Math.toRadians(hue.toDouble())
        val markerDistance = saturation * radius
        val markerX = center.x + (markerDistance * cos(angleRadians)).toFloat()
        val markerY = center.y + (markerDistance * sin(angleRadians)).toFloat()

        // Draw selection marker
        drawCircle(
            color = Color.White,
            center = Offset(markerX, markerY),
            radius = 8.dp.toPx(),
            style = Stroke(width = 3.dp.toPx()),
        )
    }
}

@Composable
fun SaturationValueSlider(
    currentColor: Color,
    onColorChanged: (Color) -> Unit,
) {
    var hsv by remember { mutableStateOf(FloatArray(3)) }

    LaunchedEffect(currentColor) {
        currentColor.toHsv(hsv)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Slider(
            value = hsv[1],
            onValueChange = {
                hsv[1] = it
                onColorChanged(Color.hsv(hsv[0], hsv[1], hsv[2]))
            },
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.White,
            ),
        )

        Slider(
            value = hsv[2],
            onValueChange = {
                hsv[2] = it
                onColorChanged(Color.hsv(hsv[0], hsv[1], hsv[2]))
            },
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.White,
            ),
        )
    }
}

fun Color.toHsv(hsv: FloatArray) {
    val red = (red * 255).toInt()
    val green = (green * 255).toInt()
    val blue = (blue * 255).toInt()
    AndroidColor.RGBToHSV(red, green, blue, hsv)
}

fun Float.toDegrees(): Float = Math.toDegrees(this.toDouble()).toFloat()

@Preview
@Composable
private fun ColorWheelPickerScreenPreview() {
    ColorWheelPickerScreen()
}