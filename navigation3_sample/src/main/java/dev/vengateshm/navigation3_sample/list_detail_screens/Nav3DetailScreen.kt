package dev.vengateshm.navigation3_sample.list_detail_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Nav3DetailScreen(
  modifier: Modifier = Modifier,
  data: String,
) {
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {
    Text(text = data)
  }
}

@Preview
@Composable
private fun Nav3DetailScreenPreview() {
  Nav3DetailScreen(
    data = "Item 0",
  )
}