package dev.vengateshm.compose_material3.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

data class RadioOption(
  val name: String,
  val color: Color,
)

@Composable
fun RadioGroupSample(modifier: Modifier = Modifier) {
  val radioOptions = listOf(
    RadioOption(
      name = "Red",
      color = Color.Red,
    ),
    RadioOption(
      name = "Green",
      color = Color.Green,
    ),
    RadioOption(
      name = "Blue",
      color = Color.Blue,
    ),
  )
  val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(selectedOption.color.copy(alpha = 0.2f)),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {

    radioOptions.forEach { radioOption ->
      Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
          selected = selectedOption == radioOption,
          onClick = {
            onOptionSelected(radioOption)
          },
        )
        Text(text = radioOption.name, color = Color.White)
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RadioGroupSamplePreview() {
  MaterialTheme {
    RadioGroupSample()
  }
}