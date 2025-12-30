package dev.vengateshm.compose_material3.ui_components.text_fields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.insert
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val cardNumberTransformation = OutputTransformation {
  if (length >= 5) {
    insert(4, " ")
  }
  if (length >= 9) {
    insert(9, " ")
  }
  if (length >= 14) {
    insert(14, " ")
  }
}

@Preview
@Composable
fun DemoCardNumberTransformation(modifier: Modifier = Modifier) {
  val textState = rememberTextFieldState()
  OutlinedTextField(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp),
    state = textState,
    textStyle = TextStyle(fontSize = 34.sp),
    outputTransformation = cardNumberTransformation,
  )
}