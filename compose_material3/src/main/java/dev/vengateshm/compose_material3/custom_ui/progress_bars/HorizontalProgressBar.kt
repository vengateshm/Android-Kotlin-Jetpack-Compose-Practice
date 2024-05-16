package dev.vengateshm.compose_material3.custom_ui.progress_bars

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.roundToInt
import kotlin.time.Duration.Companion.seconds

@Composable
fun HorizontalProgressBar(modifier: Modifier = Modifier) {
    var progress by remember { mutableFloatStateOf(0.0f) }
    val size by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 200,
            easing = LinearOutSlowInEasing
        ),
        label = "progress size animation"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .widthIn(min = 30.dp)
                .fillMaxWidth(fraction = size),
            horizontalArrangement = if (progress == 0f) Arrangement.Start else Arrangement.End
        ) {
            val intProgress = (progress * 100).roundToInt()
            Text(text = intProgress.toString())
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = Color(0xFFEF9A9A))
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction = size)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(9.dp))
                    .background(color = Color(0xFFD32F2F))
                    .animateContentSize()
            )
        }
    }

    LaunchedEffect(key1 = Unit) {
        for (i in 1..10) {
            delay(1.seconds)
            progress = i / 10f
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HorizontalProgressBarPreview() {
    HorizontalProgressBar()
}