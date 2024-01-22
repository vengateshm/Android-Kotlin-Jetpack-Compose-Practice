package dev.vengateshm.android_kotlin_compose_practice.speedometer

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun SpeedoMeterScreen() {
    var targetValue by remember {
        mutableStateOf(0f)
    }
    val progress =
        remember(targetValue) {
            Animatable(initialValue = 0f)
        }
    val scope = rememberCoroutineScope()

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Slider(
            value = targetValue,
            onValueChange = {
                targetValue = it
            },
        )

        val intValue = targetValue * 55
        Text(
            text = "${intValue.toInt()}",
        )
        Button(
            onClick = {
                scope.launch {
                    progress.animateTo(
                        targetValue = intValue,
                        animationSpec = /*tween(
                            durationMillis = 1000,
                            easing = FastOutLinearInEasing
                        )*/
                            spring(dampingRatio = Spring.DampingRatioHighBouncy),
                    )
                }
            },
        ) {
            Text(
                text = "Go",
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        SpeedoMeter(progress = progress.value.toInt())
    }
}
