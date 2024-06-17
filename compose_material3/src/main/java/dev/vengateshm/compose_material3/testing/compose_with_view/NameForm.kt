package dev.vengateshm.compose_material3.testing.compose_with_view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

const val NAME_FORM_INPUT_TAG = "NAME_FORM_INPUT_TAG"
const val NAME_FORM_CONTINUE_BTN_TAG = "NAME_FORM_CONTINUE_BTN_TAG"

@Composable
fun NameForm(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var enabled by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(alignment = Alignment.Center)
        ) {
            Text(text = "Name")
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.testTag(NAME_FORM_INPUT_TAG),
                value = name,
                onValueChange = {
                    name = it
                    enabled = it.length >= 3
                },
                label = {
                    Text(text = "Name")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                enabled = enabled,
                modifier = Modifier.testTag(NAME_FORM_CONTINUE_BTN_TAG),
                onClick = {

                }) {
                Text(text = "CONTINUE")
            }
        }
    }
}