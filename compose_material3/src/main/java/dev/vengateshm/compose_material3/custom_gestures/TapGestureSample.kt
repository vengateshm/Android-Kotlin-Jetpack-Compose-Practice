package dev.vengateshm.compose_material3.custom_gestures

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun TapGestureSample(modifier: Modifier = Modifier) {
    var tapCount by remember { mutableIntStateOf(0) }
    var tapText by remember { mutableStateOf("Tap:") }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = tapText)
        Box(modifier = Modifier
            .size(100.dp)
            .background(color = Color.Gray)
            .pointerInput(Unit) {
                detectTapGestures {
                    tapCount += 1
                    tapText = "Tap $tapCount"
                }
            })
    }
}