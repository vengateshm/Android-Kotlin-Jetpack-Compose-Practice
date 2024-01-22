package dev.vengateshm.android_kotlin_compose_practice.stopwatch

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vengateshm.android_kotlin_compose_practice.stopwatch.Constants.ACTION_SERVICE_CANCEL
import dev.vengateshm.android_kotlin_compose_practice.stopwatch.Constants.ACTION_SERVICE_START
import dev.vengateshm.android_kotlin_compose_practice.stopwatch.Constants.ACTION_SERVICE_STOP
import dev.vengateshm.android_kotlin_compose_practice.stopwatch.service.StopWatchService

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(stopWatchService: StopWatchService) {
    val context = LocalContext.current
    val hours by stopWatchService.hours
    val minutes by stopWatchService.minutes
    val seconds by stopWatchService.seconds
    val currentState by stopWatchService.currentState

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(color = Color.Black)
                .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            AnimatedContent(
                targetState = seconds,
                transitionSpec = { addAnimation() },
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = hours,
                    style =
                        TextStyle(
                            fontSize = MaterialTheme.typography.h1.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = if (hours == "00") Color.White else Color.Blue,
                        ),
                )
            }
            AnimatedContent(
                targetState = seconds,
                transitionSpec = { addAnimation() },
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = minutes,
                    style =
                        TextStyle(
                            fontSize = MaterialTheme.typography.h1.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = if (minutes == "00") Color.White else Color.Blue,
                        ),
                )
            }
            AnimatedContent(
                targetState = seconds,
                transitionSpec = { addAnimation() },
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = seconds,
                    style =
                        TextStyle(
                            fontSize = MaterialTheme.typography.h1.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = if (seconds == "00") Color.White else Color.Blue,
                        ),
                )
            }
        }
        Row(
            modifier = Modifier.weight(1f),
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    ServiceHelper.triggerForegroundService(
                        context = context,
                        action =
                            if (currentState == StopWatchState.Started) {
                                ACTION_SERVICE_STOP
                            } else {
                                ACTION_SERVICE_START
                            },
                    )
                },
                colors =
                    ButtonDefaults.buttonColors(
                        backgroundColor = if (currentState == StopWatchState.Started) Color.Red else Color.Blue,
                        contentColor = Color.White,
                    ),
            ) {
                Text(
                    text =
                        if (currentState == StopWatchState.Started) {
                            "Stop"
                        } else if (currentState == StopWatchState.Stopped) {
                            "Resume"
                        } else {
                            "Start"
                        },
                )
            }
            Spacer(modifier = Modifier.width(30.dp))
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    ServiceHelper.triggerForegroundService(
                        context = context,
                        action = ACTION_SERVICE_CANCEL,
                    )
                },
                enabled = seconds != "00" && currentState != StopWatchState.Started,
                colors =
                    ButtonDefaults.buttonColors(
                        backgroundColor = if (currentState == StopWatchState.Started) Color.Red else Color.Blue,
                        contentColor = Color.White,
                    ),
            ) {
                Text(
                    text = "Cancel",
                )
            }
        }
    }
}

@ExperimentalAnimationApi
fun addAnimation(duration: Int = 600): ContentTransform {
    return slideInVertically(
        animationSpec = tween(durationMillis = duration),
        initialOffsetY = { height ->
            height
        },
    ) with
        slideOutVertically(
            animationSpec = tween(durationMillis = duration),
            targetOffsetY = { height ->
                height
            },
        )
}
