package dev.vengateshm.compose_material3.ui_components

import androidx.compose.material3.RangeSlider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RangeSliderSample() {
    val position = remember {
        mutableStateOf(30f..100f)
    }
    RangeSlider(
        value = position.value,
        onValueChange = {
            position.value = it
        },
        valueRange = 0f..100f
    )
}

@Preview
@Composable
private fun RangeSliderSamplePreview() {
    RangeSliderSample()
}