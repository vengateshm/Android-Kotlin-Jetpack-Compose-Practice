package dev.vengateshm.navigation3_sample.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ScreenWithAButton(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    Button(onClick = onClick) {
      Text(text = text)
    }
  }
}

@Preview
@Composable
private fun ScreenWithAButtonPreview() {
  ScreenWithAButton(
    text = "Text",
    onClick = {},
  )
}