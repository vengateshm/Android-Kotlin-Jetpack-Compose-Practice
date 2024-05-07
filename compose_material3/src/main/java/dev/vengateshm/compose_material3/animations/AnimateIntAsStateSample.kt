package dev.vengateshm.compose_material3.animations

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AnimateIntAsStateSample() {
    var animateText by remember { mutableStateOf(false) }
    val textValue by animateIntAsState(
        targetValue = if (animateText) 50 else 0,
        animationSpec = tween(durationMillis = 1500),
        label = ""
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "$textValue")
            Button(onClick = { animateText = !animateText }) {
                Text(text = "Animate")
            }
        }
    }
}