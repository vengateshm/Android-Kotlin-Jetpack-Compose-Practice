package dev.vengateshm.compose_material3.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.theme.Material3AppTheme

@Composable
fun AnimatableArcSample(modifier: Modifier = Modifier) {
    val animatable = remember {
        Animatable(initialValue = 0f)
    }

    LaunchedEffect(key1 = Unit) {
        animatable.animateTo(
            1f,
            animationSpec = tween(durationMillis = 4000)
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = modifier
                .size(400.dp)
                .background(color = Color(0xFFFAF6F7))
        ) {
            val width = size.width
            val height = size.height
            drawLine(
                color = Color.Black,
                start = Offset(x = 0f, y = height / 2),
                end = Offset(x = width, y = height / 2)
            )
            drawLine(
                color = Color.Black,
                start = Offset(x = width / 2, y = 0f),
                end = Offset(x = width / 2, y = height)
            )
            drawArc(
                color = Color(0xFFE91E63),
                startAngle = 0f,
                sweepAngle = 360f * animatable.value,
                useCenter = false,
                topLeft = Offset(x = center.x - 100.dp.toPx()/2, y = center.y - 100.dp.toPx()/2),
                size = Size(100.dp.toPx(), 100.dp.toPx()),
                style = Stroke(width = 25f, cap = StrokeCap.Round)
            )
        }
    }
}

@Preview
@Composable
private fun AnimatableArcSamplePreview() {
    Material3AppTheme {
        AnimatableArcSample()
    }
}