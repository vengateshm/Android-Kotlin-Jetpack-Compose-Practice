package dev.vengateshm.compose_material3.testing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun StateRestorationTestSample(modifier: Modifier = Modifier) {

//    var text by remember { mutableStateOf("") }
    var text by rememberSaveable { mutableStateOf("") }
    var text1 by rememberSaveable { mutableStateOf("Loading...") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Label") },
            modifier = Modifier.testTag("TextField"),
        )

        Text(modifier = Modifier.testTag("TT"), text = text1)
        Button(
            modifier = Modifier.testTag("BB"),
            onClick = {
                text1 = "Loaded"
            },
        ) {
            Text("Click me!")
        }
    }
}

@Preview
@Composable
private fun StateRestorationTestSamplePreview() {
    StateRestorationTestSample()
}