package dev.vengateshm.compose_material3.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySlider() {
    val offset = remember { mutableStateOf(0f) }
    Slider(
        value = offset.value,
        onValueChange = {
            offset.value = it
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRangeSlider() {
    val position = remember {
        mutableStateOf(0f..100f)
    }
    RangeSlider(
        value = position.value,
        onValueChange = {
            position.value = it
        },
        valueRange = 0f..100f
    )
}