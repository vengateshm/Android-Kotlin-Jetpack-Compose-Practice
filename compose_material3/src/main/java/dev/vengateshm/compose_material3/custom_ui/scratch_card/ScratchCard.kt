package dev.vengateshm.compose_material3.custom_ui.scratch_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun ScratchCard(modifier: Modifier = Modifier) {
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {
    Box(
      modifier = Modifier
        .size(280.dp)
        .clip(RoundedCornerShape(16.dp)),
      contentAlignment = Alignment.Center,
    ) {
      InnerLayer()
      OuterLayer()
    }
  }
}