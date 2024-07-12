package dev.vengateshm.compose_material3.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationEndReason
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonColorAnimationSample(modifier: Modifier = Modifier) {
    ButtonColorAnimation()
}

@Composable
fun ButtonColorAnimation() {
    var isSubscribed by remember { mutableStateOf(false) }
    var state by remember { mutableStateOf(SubscribeButtonState.Start) }

    val progress = remember { Animatable(0f) }
    LaunchedEffect(isSubscribed) {
        if (isSubscribed) {
            state = SubscribeButtonState.Animating
            val animationResult = progress.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 500, easing = LinearEasing),
            )
            if (animationResult.endReason == AnimationEndReason.Finished) {
                state = SubscribeButtonState.End
            }
        } else {
            state = SubscribeButtonState.Start
            progress.snapTo(targetValue = 0f)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .clickable { isSubscribed = isSubscribed.not() }
                .clip(RoundedCornerShape(25.dp))
                .buttonBackgroundModifier(
                    isClicked = isSubscribed,
                    state = state,
                    progress = progress.value
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Center) {
            Text(
                text = if (isSubscribed) "Subscribed" else "Subscribe",
                color = if (isSubscribed) Color.Black else Color.White
            )
        }
    }
}

enum class SubscribeButtonState {
    Start,
    Animating,
    End
}

fun Modifier.buttonBackgroundModifier(
    isClicked: Boolean,
    state: SubscribeButtonState,
    progress: Float,
): Modifier {
    return this.drawWithContent {
        if (isClicked) {
            if (state == SubscribeButtonState.End) {
                drawRoundRect(color = Color(0xFFBBBBBB))
            } else {
                val width = size.width
                val height = size.height
                val offset = progress * width

                val brush = Brush.linearGradient(
                    listOf(Color(0xFFC7B552), Color(0xFF279059), Color(0xFF000000)),
                    start = Offset(offset, 0f),
                    end = Offset(offset + width, height)
                )
                drawRoundRect(brush = brush)
            }
        } else {
            drawRoundRect(color = Color(0xFF000000))
        }
        drawContent()
    }
}