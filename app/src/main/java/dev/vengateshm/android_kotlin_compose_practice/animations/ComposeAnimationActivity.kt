package dev.vengateshm.android_kotlin_compose_practice.animations

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class ComposeAnimationActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
//                AnimationSample()
//                CounterAnimation()
                val displayMetrics = DisplayMetrics()
                windowManager.defaultDisplay.getMetrics(displayMetrics)
                val width = displayMetrics.widthPixels
                val height = displayMetrics.heightPixels
//                InfiniteFloatTransition(width,height)
                InfiniteColorTransition()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationSample() {
    Column(modifier = Modifier.fillMaxSize()) {
        var isVisible by remember { mutableStateOf(false) }
        var isRound by remember { mutableStateOf(false) }
        Button(onClick =
        {
            isVisible = !isVisible
            isRound = !isRound
        }) {
            Text(text = "Toggle")
        }
        /*AnimatedVisibility(visible = isVisible,
            enter = slideInHorizontally() + fadeIn(),
            exit = slideOutHorizontally() + fadeOut(),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
            Box(modifier = Modifier.background(Color.Blue))
        }*/

        val borderRadius by animateIntAsState(
            targetValue = if (isRound) 100 else 0,
            animationSpec =
            tween(
                durationMillis = 3000,
                delayMillis = 500,
            )
            /*spring(// Changes target value beyond the min max limit
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessVeryLow
            )*/
            /*keyframes {  }*/
        )

        // Updating multiple items
        /*val transition = updateTransition(
            targetState = isRound,
            label = null,
        )

        val bordRad by transition.animateInt(
            transitionSpec = { tween(durationMillis = 2000) },
            label = "bordRadius",
            targetValueByState = { value ->
                if (value) 50 else 0
            }
        )

        val boxColor by transition.animateColor(
            transitionSpec = { tween(durationMillis = 1000) },
            label = "boxColor",
            targetValueByState = { value ->
                if (value) Color.Red else Color.Gray
            }
        )

        Box(modifier = Modifier
            .size(200.dp)
            .clip(RoundedCornerShape(bordRad))
            .background(boxColor)) {

        }*/

        /*val infiniteTransition = rememberInfiniteTransition()
        val color by infiniteTransition.animateColor(initialValue = Color.Red,
            targetValue = Color.Green,
            animationSpec = infiniteRepeatable(
                animation = tween(2000),
                repeatMode = RepeatMode.Reverse
            ))
        Box(modifier = Modifier
            .size(200.dp)
            .clip(RoundedCornerShape(bordRad))
            .background(color)) {

        }*/

        /*Crossfade() {

        }*/
        AnimatedContent(
            targetState = isVisible,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            content = { value ->
                if (value) {
                    Box(
                        modifier = Modifier
                            .background(Color.Red)
                    ) {

                    }
                } else {
                    Box(
                        modifier = Modifier
                            .background(Color.Green)
                    ) {

                    }
                }
            },
            transitionSpec = {
                slideInHorizontally(
                    initialOffsetX = {
//                                    -it
//                                    -it / 2
                        if (isVisible) it else -it
                    }
                ) with slideOutHorizontally {
//                                it
//                                it / 2
                    if (isVisible) -it else it
                }
            })
    }
}