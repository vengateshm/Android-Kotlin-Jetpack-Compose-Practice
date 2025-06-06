package dev.vengateshm.compose_material3.animations

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AnimatedTextReveal(
    text: String,
) {
    var textToReveal by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        textToReveal = ""
        for (i in text.indices) {
            delay(1000)
            textToReveal += text[i]
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(0.5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        textToReveal.forEachIndexed { index, char ->
            val rotation = remember { Animatable(0f) }
            val scale = remember { Animatable(3f) }
            val alpha = remember { Animatable(0.3f) }

            LaunchedEffect(textToReveal.length) {
                if (index == textToReveal.lastIndex) {
                    launch { scale.animateTo(1f, animationSpec = spring()) }
                    launch { rotation.animateTo(360f, animationSpec = tween(600)) }
                    launch { alpha.animateTo(1f, animationSpec = tween(600)) }
                }
            }

            Text(
                text = char.toString(),
                fontSize = 32.sp,
                color = Color.Blue.copy(alpha = alpha.value),
                modifier = Modifier
                    .graphicsLayer(
                        rotationZ = rotation.value,
                        scaleX = scale.value,
                        scaleY = scale.value,
                    )
                    .padding(end = 4.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimatedTextRevealPreview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AnimatedTextReveal(text = "Compose")
    }
}