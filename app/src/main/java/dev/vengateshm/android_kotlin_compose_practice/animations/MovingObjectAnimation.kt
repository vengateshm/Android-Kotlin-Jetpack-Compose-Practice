package dev.vengateshm.android_kotlin_compose_practice.animations

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.vengateshm.android_kotlin_compose_practice.R
import kotlinx.coroutines.delay
import kotlin.math.pow

@Composable
fun MovingObjectAnimation() {

    var isMovingRight by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        while (true) {
            delay(2000L)
            isMovingRight = true
            delay(2000L)
            isMovingRight = false
        }
    }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val boxSize = 60.dp

    // Calculate the target value based on screen width and box size
    val targetXTranslation = screenWidth - boxSize

    val linearEasingX by animateDpAsState(
        targetValue = if (isMovingRight) targetXTranslation else 0.dp,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        label = "linearEasing"
    )
    val easeInOutCubicX by animateDpAsState(
        targetValue = if (isMovingRight) targetXTranslation else 0.dp,
        animationSpec = tween(
            durationMillis = 1000,
            easing = CubicBezierEasing(0.42f, 0f, 0.58f, 1f)
        ),
        label = "easeInOutCubic"
    )
    val fastOutSlowInEasingX by animateDpAsState(
        targetValue = if (isMovingRight) targetXTranslation else 0.dp,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "fastOutSlowInEasing"
    )
    val linearOutSlowInEasingX by animateDpAsState(
        targetValue = if (isMovingRight) targetXTranslation else 0.dp,
        animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing),
        label = "linearOutSlowInEasing"
    )
    val customEasingX by animateDpAsState(
        targetValue = if (isMovingRight) targetXTranslation else 0.dp,
        animationSpec = tween(durationMillis = 1000, easing = CustomEasing(2f)),
        label = "customEasing"
    )
    val springX by animateDpAsState(
        targetValue = if (isMovingRight) targetXTranslation else 0.dp,
        animationSpec = spring(dampingRatio = 0.8f, stiffness = 150f),
        label = "spring"
    )
    Column(modifier = Modifier.fillMaxSize()) {
        MovingContent(x = linearEasingX)
        MovingContent(x = easeInOutCubicX)
        MovingContent(x = fastOutSlowInEasingX)
        MovingContent(x = linearOutSlowInEasingX)
        MovingContent(x = customEasingX)
        MovingContent(x = springX)
    }
}

@Composable
fun MovingContent(x: Dp) {
    Box(
        modifier = Modifier
            .offset(x = x)
            .size(60.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.cycling),
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun MovingObjectAnimationPreview() {
    MovingObjectAnimation()
}

class CustomEasing(private val factor: Float) : Easing {
    override fun transform(fraction: Float): Float {
        return fraction.toDouble().pow(factor.toDouble()).toFloat()
    }

}