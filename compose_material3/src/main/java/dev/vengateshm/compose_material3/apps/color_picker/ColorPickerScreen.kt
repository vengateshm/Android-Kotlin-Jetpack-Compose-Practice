package dev.vengateshm.compose_material3.apps.color_picker

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ColorPickerScreen(viewModel: ColorPickerViewModel = viewModel()) {
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
            ColorPalette(
                colors = colorPalette,
                selectedColor = currentColor,
                onColorSelected = { viewModel.updateColor(it) },
            )
            ColorPalette(
                colors = colorPalette2,
                selectedColor = currentColor,
                onColorSelected = { viewModel.updateColor(it) },
            )
        }
    }
}

@Composable
fun ColorInfoDisplay(color: Color, textColor: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "HEX: ${color.toHex()}",
            style = MaterialTheme.typography.headlineMedium,
            color = textColor
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "RGB: ${color.toRGB()}",
            style = MaterialTheme.typography.headlineMedium,
            color = textColor
        )
    }
}

@Composable
fun ColorPalette(
    colors: List<Color>,
    selectedColor: Color,
    onColorSelected: (Color) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        colors.forEach { color ->
            CircularColorButton(
                color = color,
                isSelected = color == selectedColor,
                onClick = { onColorSelected(color) }
            )
        }
    }
}

@Composable
fun CircularColorButton(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = if (isSelected) Color.White else Color.Transparent

    Surface(
        onClick = onClick,
        modifier = modifier
            .size(48.dp),
        shape = CircleShape,
        color = color,
        border = BorderStroke(
            width = 3.dp,
            color = borderColor
        )
    ) {}
}

// Color extension functions
fun Color.toHex(): String {
    val argb = toArgb()
    return String.format("#%08X", argb)
}

fun Color.toRGB(): String {
    val red = (red * 255).toInt()
    val green = (green * 255).toInt()
    val blue = (blue * 255).toInt()
    return "($red, $green, $blue)"
}

// Color palettes
val colorPalette = listOf(
    Color.Red,
    Color.Green,
    Color.Blue,
    Color.Yellow,
    Color.Magenta,
    Color.Cyan
)

val colorPalette2 = listOf(
    Color(0xFF4CAF50),
    Color(0xFF9C27B0),
    Color(0xFF2196F3),
    Color(0xFFFF9800),
    Color(0xFF795548),
    Color(0xFF607D8B)
)

@Preview
@Composable
private fun ColorPickerScreenPreview() {
    ColorPickerScreen()
}