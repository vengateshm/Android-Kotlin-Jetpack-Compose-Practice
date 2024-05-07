package dev.vengateshm.compose_material3.custom_ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LanguagesCheckBoxSample(modifier: Modifier = Modifier) {
    val options = remember { listOf("English", "French", "German") }
    val selectedOptions = remember {
        mutableStateMapOf<Int, Boolean>().withDefault { false }
    }
    Column(
        modifier = modifier
    ) {
        options.forEachIndexed { index, option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .toggleable(
                        value = selectedOptions.getValue(index),
                        onValueChange = {
                            selectedOptions[index] = it
                        }
                    )
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = selectedOptions.getValue(index),
                    onCheckedChange = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = option)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LanguagesCheckBoxSamplePreview() {
    LanguagesCheckBoxSample()
}