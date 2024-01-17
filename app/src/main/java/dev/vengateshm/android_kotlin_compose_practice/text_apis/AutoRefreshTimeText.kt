package dev.vengateshm.android_kotlin_compose_practice.text_apis

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun AutoRefreshTimeText(timeStampInMillis: Long, refreshTimeInMillis: Long = 10_000) {

    var formattedTime by remember(key1 = timeStampInMillis) {
        mutableStateOf(formatTime(timeStampInMillis))
    }

    val coroutineScope = rememberCoroutineScope()

    // Refreshing every X milliseconds using
    DisposableEffect(timeStampInMillis) {
        val refreshJob = coroutineScope.launch {
            while (true) {
                delay(refreshTimeInMillis)
                formattedTime = formatTime(timeStampInMillis)
            }
        }

        onDispose {
            refreshJob.cancel()
        }
    }

    Text(text = formattedTime)
}

// Format timestamp
fun formatTime(timeStampInMillis: Long): String {
    val elapsedTimeInMillis = System.currentTimeMillis() - timeStampInMillis

    val seconds = elapsedTimeInMillis / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val weeks = days / 7
    val months = days / 30
    val years = days / 365

    return when {
        years > 0 -> "$years years ago"
        months > 0 -> "$months months ago"
        weeks > 0 -> "$weeks weeks ago"
        days > 0 -> "$days days ago"
        hours > 0 -> "$hours hours ago"
        minutes > 0 -> "$minutes minutes ago"
        else -> if (seconds > 0) "$seconds seconds ago" else "just now"
    }
}

@Composable
fun AutoRefreshTimeTextSample() {
    var timeStampInMillis by remember {
        mutableLongStateOf(System.currentTimeMillis())
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AutoRefreshTimeText(
            timeStampInMillis = timeStampInMillis
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { timeStampInMillis = System.currentTimeMillis() }) {
            Text(text = "Set Time")
        }
    }
}