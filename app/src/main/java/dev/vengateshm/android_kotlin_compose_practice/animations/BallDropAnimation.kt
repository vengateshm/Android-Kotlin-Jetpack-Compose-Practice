package dev.vengateshm.android_kotlin_compose_practice.animations

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import dev.vengateshm.android_kotlin_compose_practice.R
import kotlinx.coroutines.delay

@Composable
fun BallDropAnimation() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val boxSize = 60.dp

    var dropBall by remember { mutableStateOf(false) }
    LaunchedEffect(true) {
        delay(2000L)
        dropBall = true
    }

    val xOffset = (screenWidth / 2) - (boxSize / 2)
    val initialYOffset = (screenHeight / 2) - (boxSize / 2)
    val yOffset by animateDpAsState(
        targetValue = if (dropBall) (screenHeight - boxSize) else initialYOffset,
        animationSpec =
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow,
            ),
        label = "ballBounceAnimation",
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier =
                Modifier
                    .offset(
                        x = xOffset,
                        y = yOffset,
                    )
                    .size(boxSize),
        ) {
            Image(
                painter = painterResource(id = R.drawable.basketball),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
