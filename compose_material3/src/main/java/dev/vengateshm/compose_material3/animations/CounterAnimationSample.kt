package dev.vengateshm.compose_material3.animations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun CounterAnimationSample() {

    var counter by remember {
        mutableIntStateOf(0)
    }

    var isRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(1000L)
            counter++
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedContent(
            targetState = counter,
            transitionSpec = {
                textAnimation().using(SizeTransform(clip = false))
            },
            label = "counterAnimatedContent"
        ) { state ->
            Text(
                text = state.toString(),
                fontSize = 200.sp
            )
        }

        Spacer(modifier = Modifier.height(100.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                isRunning = !isRunning
            }) {
                Text(text = if (isRunning) "Stop" else "Start")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                counter = 0
                isRunning = false
            }) {
                Text(text = "Reset")
            }
        }
    }
}

fun textAnimation(duration: Int = 800): ContentTransform {
    return (
            slideInVertically(
                animationSpec = tween(durationMillis = duration)
            )
            { height -> height } + fadeIn(
                animationSpec = tween(durationMillis = duration)
            )
            ).togetherWith(
            slideOutVertically(
                animationSpec = tween(durationMillis = duration)
            ) { height -> height } + fadeOut(
                animationSpec = tween(durationMillis = duration)
            )
        )
}