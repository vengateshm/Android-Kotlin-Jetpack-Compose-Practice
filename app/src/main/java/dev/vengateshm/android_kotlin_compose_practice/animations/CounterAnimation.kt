package dev.vengateshm.android_kotlin_compose_practice.animations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CounterAnimation() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var count by remember { mutableStateOf(0) }
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                println("initial state $initialState")
                println("target state $targetState")
                if (targetState > initialState) {
                    slideInVertically(initialOffsetY = { it }) + fadeIn() with
                            slideOutVertically(targetOffsetY = { -it }) + fadeOut()
                } else {
                    slideInVertically(initialOffsetY = { -it }) + fadeIn() with
                            slideOutVertically(targetOffsetY = { it }) + fadeOut()
                }.using(SizeTransform(clip = false))
            }
        ) { targetCount ->
            Text(
                text = "$targetCount",
                fontSize = 150.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { count++ }) {
                Text(text = "+")
            }
            Button(onClick = { count-- }) {
                Text(text = "-")
            }
        }
    }
}