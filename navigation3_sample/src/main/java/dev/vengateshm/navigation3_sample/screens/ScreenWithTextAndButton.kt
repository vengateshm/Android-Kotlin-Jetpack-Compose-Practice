package dev.vengateshm.navigation3_sample.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ScreenWithTextAndButton(
  text: String,
  buttonText: String,
  onButtonClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {
    Text(text)
    Button(onClick = onButtonClick) {
      Text(buttonText)
    }
  }
}

@Preview
@Composable
private fun ScreenWithTextAndButtonPreview() {
  ScreenWithTextAndButton(
    text = "Text",
    buttonText = "Button",
    onButtonClick = {},
  )
}