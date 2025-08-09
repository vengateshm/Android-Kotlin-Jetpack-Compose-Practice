package dev.vengateshm.compose_material3.apps.math_game

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnswerField(
  modifier: Modifier = Modifier,
  text: String,
  onAnswerTextChange: (String) -> Unit,
) {
  TextField(
    value = text,
    onValueChange = onAnswerTextChange,
    label = { Text("Enter your answer") },
    colors = TextFieldDefaults.colors(
      focusedLabelColor = Color.White,
      unfocusedLabelColor = Color.White,
      focusedIndicatorColor = Color.Transparent,
      unfocusedIndicatorColor = Color.Transparent,
      focusedContainerColor = Color(0XFF54A3A9),
      unfocusedContainerColor = Color(0XFF54A3A9),
      focusedTextColor = Color.White,
      unfocusedTextColor = Color.White,
      cursorColor = Color.White,
    ),
    modifier = modifier.size(width = 300.dp, height = 75.dp),
    textStyle = TextStyle(
      fontSize = 24.sp, textAlign = TextAlign.Center,
    ),
    shape = RoundedCornerShape(percent = 0),
    keyboardOptions = KeyboardOptions(
      keyboardType = KeyboardType.Number,
    ),
  )
}

@Preview
@Composable
private fun AnswerFieldPreview() {
  AnswerField(
    text = "70",
    onAnswerTextChange = {},
  )
}