package dev.vengateshm.compose_material3.animation

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun RememberInfiniteTransitionSample() {
    TextZoomInZoomOut(
        modifier = Modifier.size(200.dp)
    )
}

@Composable
fun TextZoomInZoomOut(
    modifier: Modifier = Modifier
) {

    val rememberInfiniteTransitionTextZoomInZoomOut =
        rememberInfiniteTransition(label = "rememberInfiniteTransitionTextZoomInZoomOut")

    val scaleXY = rememberInfiniteTransitionTextZoomInZoomOut
        .animateFloat(
            initialValue = 1f,
            targetValue = 6f,
            animationSpec = infiniteRepeatable(tween(durationMillis = 1000)),
            label = ""
        )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Kotlin",
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scaleXY.value
                    scaleY = scaleXY.value
                })
    }
}