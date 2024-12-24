package dev.vengateshm.compose_material3.ml_kit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SmartRepliesSampleRoot(modifier: Modifier = Modifier) {
    val viewModel = viewModel<SmartRepliesViewModel>()
    var localUserText by remember { mutableStateOf("") }
    var remoteUserText by remember { mutableStateOf("") }

    SmartRepliesSample(
        onLocalUserTextChange = {
            localUserText = it
        },
        onRemoteUserTextChange = {
            remoteUserText = it
        },
        onAddRemoteUserText = {
            viewModel.addRemoteUserText(it)
            remoteUserText = ""
        },
        onAddLocalUserText = {
            viewModel.addLocalUserText(it)
            localUserText = ""
        },
        onGenerateReplies = {
            viewModel.generateReply()
        },
        localUserText = {
            localUserText
        },
        remoteUserText = {
            remoteUserText
        },
        replies = viewModel.suggestions,
    )
}

@Composable
fun SmartRepliesSample(
    modifier: Modifier = Modifier,
    onLocalUserTextChange: (String) -> Unit = {},
    onRemoteUserTextChange: (String) -> Unit = {},
    onAddLocalUserText: (String) -> Unit = {},
    onAddRemoteUserText: (String) -> Unit = {},
    onGenerateReplies: () -> Unit = {},
    localUserText: () -> String = { "" },
    remoteUserText: () -> String = { "" },
    replies: List<String> = emptyList(),
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = localUserText(),
            onValueChange = onLocalUserTextChange,
            label = { Text(text = "Local user text") },
        )
        Button(
            onClick = {
                onAddLocalUserText(localUserText())
            },
        ) {
            Text(text = "Add local user text")
        }
        OutlinedTextField(
            value = remoteUserText(),
            onValueChange = onRemoteUserTextChange,
            label = { Text(text = "Remote user text") },
        )
        Button(
            onClick = {
                onAddRemoteUserText(remoteUserText())
            },
        ) {
            Text(text = "Add remote user text")
        }
        Button(
            onClick = {
                onGenerateReplies()
            },
        ) {
            Text(text = "Generate replies")
        }
        replies.forEach {
            AssistChip(onClick = { }, label = { Text(text = it) })
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, fontScale = 1f)
@Composable
private fun SmartRepliesSamplePreview() {
    MaterialTheme {
        SmartRepliesSample()
    }
}