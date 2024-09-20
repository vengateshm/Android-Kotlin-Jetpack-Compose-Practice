package dev.vengateshm.compose_material3.other_concepts.upload_file

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.roundToInt

@Composable
fun UploadFileSample() {
    val context = LocalContext.current
    val fileReader = remember { FileReader(context) }
    val repository = remember { UploadFileRepository(UploadFileClient.client, fileReader) }
    val viewModel = viewModel<UploadFileViewModel>(
        factory = UploadFileViewModelFactory(repository),
    )
    val state = viewModel.state
    val filePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { contentUri ->
        contentUri?.let {
            viewModel.uploadFile(contentUri)
        }
    }


    LaunchedEffect(state.errorMessage) {
        if (!state.errorMessage.isNullOrEmpty()) {
            Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            when {
                !state.isUploading -> {
                    Button(
                        onClick = {
                            filePicker.launch("*/*")
                        },
                    ) {
                        Text(text = "Pick a file")
                    }
                }

                else -> {
                    val progress by animateFloatAsState(
                        targetValue = state.progress,
                        label = "progress animation",
                        animationSpec = tween(durationMillis = 500),
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .height(16.dp),
                        )
                        Text(text = "${(state.progress * 100).roundToInt()}%")
                        Button(
                            onClick = {
                                viewModel.cancelUpload()
                            },
                        ) {
                            Text(text = "Cancel upload")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun UploadFileSamplePreview() {
    MaterialTheme {
        Surface {
            UploadFileSample()
        }
    }
}