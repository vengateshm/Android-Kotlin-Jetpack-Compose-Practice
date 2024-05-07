package dev.vengateshm.compose_material3.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun AnimatableSample() {
    var scale = remember {
        Animatable(initialValue = 1f)
    }
    var color = remember {
        androidx.compose.animation.Animatable(initialValue = Color.LightGray)
    }

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .scale(scale.value)
                .background(color = color.value, shape = CircleShape)
                .clickable {
                    // Animation will happen one after another
                    /*scale += 0.1f
                    color = Color(
                        red = Random.nextInt(255),
                        green = Random.nextInt(255),
                        blue = Random.nextInt(255)
                    )*/

                    // Animation will happen simultaneously
                    scope.launch {
                        scale.animateTo(
                            targetValue = scale.value + 0.1f,
                            animationSpec = tween(durationMillis = 1000)
                        )
                    }
                    scope.launch {
                        color.animateTo(
                            Color(
                                red = Random.nextInt(255),
                                green = Random.nextInt(255),
                                blue = Random.nextInt(255)
                            ),
                            animationSpec = tween(durationMillis = 1000)
                        )
                    }
                }
        )
    }
}