package dev.vengateshm.compose_material3.ui_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.insert
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.SpanStyle

val ColoredUSNumberTransformation = OutputTransformation {
  if (length == 10) {
    insert(0, "(")
    insert(4, ")")
    insert(5, " ")
    insert(10, "-")

    val gray = Color.LightGray
    val red = Color.Red

    addStyle(SpanStyle(color = red), 0, 5)
//    addStyle(SpanStyle(color = gray), 4, 5)
//    addStyle(SpanStyle(color = gray), 10, 11)
  }
}

@Composable
fun RichStyledTextField(modifier: Modifier = Modifier) {

  val state = rememberTextFieldState(
    initialText = "1234567890",
  )
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    BasicTextField(
      state = state,
      outputTransformation = ColoredUSNumberTransformation,
    )
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RichStyledTextFieldPreview() {
  RichStyledTextField()
}