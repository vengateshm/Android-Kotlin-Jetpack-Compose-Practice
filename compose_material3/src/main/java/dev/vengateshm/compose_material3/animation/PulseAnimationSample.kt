package dev.vengateshm.compose_material3.animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun PulseAnimationSample() {
    var isVisible by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (isVisible) {
            PulseLoadAnim(
                modifier = Modifier.fillMaxSize(),
                icon = Icons.Rounded.Mic
            )
        }

        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { isVisible = !isVisible }) {
            Text(text = "Click here!")
        }
    }
}

@Composable
fun PulseLoadAnim(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null
) {

    val rippleTransition = rememberInfiniteTransition(label = "rippleTransition")
    val rippleBoxSize by rippleTransition.animateFloat(
        initialValue = 50f,
        targetValue = 300f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        ),
        label = "rippleBoxSize"
    )
    val rippleBoxAlpha by rippleTransition.animateFloat(
        initialValue = 1f,
        targetValue = 00f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        ),
        label = "rippleBoxAlpha"
    )

    // Outer box
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // Ripple background
        Box(
            modifier = Modifier
                .size(rippleBoxSize.dp)
                .alpha(rippleBoxAlpha)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = CircleShape
                )
        )
        // Icon
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            icon?.run {
                Icon(
                    imageVector = icon,
                    tint = MaterialTheme.colorScheme.onSecondary,
                    contentDescription = null,
                )
            }
        }
    }
}