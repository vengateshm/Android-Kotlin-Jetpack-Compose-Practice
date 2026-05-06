package dev.vengateshm.compose_material3.custom_ui.scratch_card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val LightBlue = Color(0xFFE3F2FD)
val Blue = Color(0xFF1976D2)

@Preview(showBackground = true)
@Composable
fun InnerLayer(modifier: Modifier = Modifier) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(
        color = LightBlue,
      ),
    contentAlignment = Alignment.Center,
  ) {
    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      Text(
        text = "🏆",
        fontSize = 64.sp,
      )
      Spacer(
        modifier = Modifier
          .height(8.dp),
      )
      Text(
        text = "YOU WON",
        color = Blue,
        fontWeight = FontWeight.Bold,
      )
      Text(
        text = "$100",
        fontSize = 64.sp,
        color = Blue,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.ExtraBold,
      )
    }
  }
}