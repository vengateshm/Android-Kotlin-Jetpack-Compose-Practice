package dev.vengateshm.compose_material3.custom_ui.story

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun StoryProgressIndicator(
    modifier: Modifier = Modifier,
    startProgress: Boolean = false,
    onAnimationEnd: () -> Unit
) {
    var progress by remember {
        mutableFloatStateOf(0.0f)
    }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = ""
    )

    if (startProgress) {
        LaunchedEffect(Unit) {
            while (progress < 1f) {
                progress += 0.01f
                delay(50)
                // (1f -0f)/0.01f = 100
                // 100 * 50 = 5000 = 5 secs
            }
            onAnimationEnd()
        }
    }

    LinearProgressIndicator(
        modifier = modifier.padding(top = 12.dp, bottom = 12.dp),
        strokeCap = StrokeCap.Round,
        color = Color.LightGray,
        trackColor = Color.White,
        progress = {
            animatedProgress
        }
    )
}

@Preview
@Composable
fun LinearIndicatorPreview() {
    StoryProgressIndicator(
        startProgress = true
    ) {

    }
}