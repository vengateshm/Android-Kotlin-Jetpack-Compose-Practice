package dev.vengateshm.compose_material3.request_state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProgrammingLanguageScreen(viewModel: ProgrammingLanguageViewModel = viewModel()) {
    val data by viewModel.data.collectAsState(initial = RequestState.Idle)

    data.DisplayUi(
        onLoading = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
        onSuccess = {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxSize()) {
                    data.getSuccessDataOrNull()?.let { items ->
                        items.forEach { item ->
                            Text(
                                text = item,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        },
        onError = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = data.getErrorMessageOrNull() ?: "Error occurred",
                    color = MaterialTheme.colorScheme.error
                )
            }
        })
}