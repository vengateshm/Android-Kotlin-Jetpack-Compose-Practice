package dev.vengateshm.compose_material3.recompositions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProgressScreen(progressViewModel: ProgressViewModel = viewModel()) {
    val currentProgress by progressViewModel.progress.collectAsState()
    ProgressComponent(
        progress = { currentProgress },
        onClick = { progressViewModel.startProgress() },
    )
}

@Composable
fun ProgressComponent(
    progress: () -> Float,
    onClick: () -> Unit = {},
) {
    val onclick by rememberUpdatedState(onClick)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LinearProgressIndicator(
            progress = progress, // Use lambda version for better performance
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onclick) {
            Text("Start Progress")
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProgressText(
            progress = {
                progress()
            },
        )
    }
}

@Composable
fun ProgressText(
    progress: () -> Float,
) {
    Text(
        text = "Progress: %.1f%%".format(progress() * 100),
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProgressScreenPreview() {
    ProgressScreen()
}