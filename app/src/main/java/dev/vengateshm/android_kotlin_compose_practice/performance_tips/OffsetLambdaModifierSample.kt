package dev.vengateshm.android_kotlin_compose_practice.performance_tips

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay

@Composable
fun OffsetLambdaModifierSample() {
    val width = LocalConfiguration.current.screenWidthDp
    val height = LocalConfiguration.current.screenHeightDp

    val x = remember { Animatable(0.0f) }
    val y = remember { Animatable(0.0f) }

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "😀", fontSize = 22.sp, modifier = Modifier
                // Causes recomposition
                /*.offset(
                    x = x.value.dp,
                    y = y.value.dp
                )*/
                // Happens only in layout or drawing phase
                .offset {
                    IntOffset(
                        x = x.value.dp.roundToPx(),
                        y = y.value.dp.roundToPx()
                    )
                }
        )

        LaunchedEffect(true) {
            delay(2000)
            // First moves in x direction and then in y direction
//            x.animateTo(width / 2f, tween(1000))
//            y.animateTo(height / 2f, tween(1000))
            // Moves in both x and y direction simultaneously
            awaitAll(
                async { x.animateTo(width / 2f, tween(1000)) },
                async { y.animateTo(height / 2f, tween(1000)) }
            )
        }
    }
}