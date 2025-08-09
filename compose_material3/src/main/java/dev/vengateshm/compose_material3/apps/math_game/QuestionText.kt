package dev.vengateshm.compose_material3.apps.math_game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuestionText(
  modifier: Modifier = Modifier,
  text: String,
) {
  Text(
    text = text,
    fontSize = 24.sp,
    color = Color.White,
    textAlign = TextAlign.Center,
    modifier = modifier
      .background(Color(0XFF54A3A9))
      .size(width = 300.dp, height = 75.dp)
      .wrapContentHeight(),
  )
}

@Preview
@Composable
private fun QuestionTextPreview() {
  QuestionText(text = "2 + 2")
}