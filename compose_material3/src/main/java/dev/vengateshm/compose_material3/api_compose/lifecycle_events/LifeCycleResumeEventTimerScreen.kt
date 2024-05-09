package dev.vengateshm.compose_material3.api_compose.lifecycle_events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.vengateshm.compose_material3.theme.Material3AppTheme
import kotlin.time.Duration.Companion.milliseconds

@Stable
data class TimerState(
    val passedTimeInMillis: Long = 0L
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LifeCycleResumeEventTimerScreen(
    viewModel: TimerViewModel = viewModel {
        TimerViewModel()
    }
) {

    val state by viewModel.timerState.collectAsStateWithLifecycle(initialValue = TimerState())

    LifecycleResumeEffect(Unit) {
        viewModel.startTimer()
        onPauseOrDispose {
            viewModel.stopTimer()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Screen Time Tracker") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Active screen time")
            TimeDisplayText(passedTime = {
                state.passedTimeInMillis
            })
        }
    }
}

@Composable
fun TimeDisplayText(
    modifier: Modifier = Modifier,
    passedTime: () -> Long
) {
    val duration = passedTime().milliseconds
    val days = duration.inWholeDays % 24
    val hours = duration.inWholeHours % 24
    val minutes = duration.inWholeMinutes % 60
    val seconds = duration.inWholeSeconds % 60
    val milliseconds = duration.inWholeMilliseconds % 1000

    val formattedTime =
        String.format("%02d:%02d:%02d:%02d:%03d", days, hours, minutes, seconds, milliseconds)
    Text(
        text = "⏳$formattedTime",
        style = MaterialTheme.typography.headlineLarge
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TimeDisplayTextPreview() {
    Material3AppTheme {
        TimeDisplayText(passedTime = {
            129602300
        })
    }
}