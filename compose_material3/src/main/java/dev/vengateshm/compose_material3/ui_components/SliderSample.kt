package dev.vengateshm.compose_material3.ui_components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderSample() {
    val offset = remember { mutableFloatStateOf(0f) }
    Slider(
        value = offset.floatValue,
        onValueChange = {
            offset.floatValue = it
        },
        steps = 0,// No step shown
        valueRange = 0f..1f,
        thumb = {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize),
                tint = Color.Red
            )
        }
    )
}

@Preview
@Composable
private fun SliderSamplePreview() {
    SliderSample()
}