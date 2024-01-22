package dev.vengateshm.android_kotlin_compose_practice.message_queue_animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.LinkedList
import java.util.Queue

@Composable
fun MessageQueueAnimation() {
    var messageCount by remember {
        mutableIntStateOf(0)
    }
    var currentMessageNum by remember {
        mutableIntStateOf(0)
    }
    var showMessage by remember {
        mutableStateOf(false)
    }
    var isProcessingMessage by remember {
        mutableStateOf(false)
    }

    val animationDuration = 500L
    val messageShowDuration = 1000L

    val scope = rememberCoroutineScope()

    val messageQueue: Queue<Int> =
        remember {
            LinkedList()
        }

    LaunchedEffect(messageCount) {
        if (messageCount <= 0) return@LaunchedEffect

        messageQueue.add(messageCount)
        if (!isProcessingMessage) {
            isProcessingMessage = true
            scope.launch(Dispatchers.Main.immediate) {
                while (messageQueue.isNotEmpty()) {
                    currentMessageNum = messageQueue.remove()
                    showMessage = true
                    delay(animationDuration + messageShowDuration)
                    showMessage = false
                    delay(animationDuration)
                }
                isProcessingMessage = false
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = {
            messageCount = messageCount.inc()
        }) {
            Text(text = "Send Message")
        }
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedVisibility(visible = showMessage) {
            Text(
                modifier =
                    Modifier
                        .background(
                            color = Color(0XFFFF0000).copy(alpha = 0.2f),
                            shape = RoundedCornerShape(8.dp),
                        )
                        .padding(8.dp),
                text = "Message $currentMessageNum",
                color = Color(0XFFFF0000),
            )
        }
    }
}
