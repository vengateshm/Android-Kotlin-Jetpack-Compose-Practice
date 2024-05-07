package dev.vengateshm.compose_material3.ui_concepts.ui_and_state_in_functional_way

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UIInFunctionalWayImplScreen(viewModel: UIInFunctionalWayImplScreenViewModel = viewModel()) {
    val data by viewModel.data.collectAsState(initial = RequestState.Idle)
    UIInFunctionalWayImpl(data = data)
}

@Composable
private fun UIInFunctionalWayImpl(data: RequestState<List<String>>) {
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun UIInFunctionalWayImplScreenPreview() {
    UIInFunctionalWayImpl(
        data = RequestState.Error(message = "Error occurred")
    )
}