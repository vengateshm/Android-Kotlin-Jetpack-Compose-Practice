package dev.vengateshm.android_kotlin_compose_practice.performance_tips

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import dev.vengateshm.android_kotlin_compose_practice.R
import kotlinx.coroutines.delay

@Composable
fun GraphicsLambdaModifierSample() {

    val rotation = remember { Animatable(0.0f) }

    Box(modifier = Modifier.fillMaxSize()) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                // Causes recomposition
//                .rotate(rotation.value)
                // Avoids recomposition
                .graphicsLayer {
                    rotationZ = rotation.value
                },
            painter = painterResource(id = R.drawable.baseline_3d_rotation_24),
            contentDescription = null
        )

        LaunchedEffect(Unit) {
            delay(2000)
            rotation.animateTo(360f, tween(1000))
        }
    }
}