package dev.vengateshm.compose_material3.custom_ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GenderRadioSample(modifier: Modifier = Modifier) {
    val options = remember { listOf("Male", "Female") }
    var selectedOptionIndex by remember { mutableIntStateOf(-1) }

    Column(modifier = modifier) {
        options.forEachIndexed { index, option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedOptionIndex = index
                    }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOptionIndex == index,
                    onClick = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = option)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GenderRadioPreview() {
    GenderRadioSample()
}