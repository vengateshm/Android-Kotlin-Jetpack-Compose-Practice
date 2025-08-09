package dev.vengateshm.compose_material3.apps.math_game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MathAppButton(
  modifier: Modifier = Modifier,
  text: String,
  onClick: () -> Unit,
  isEnabled: Boolean,
) {
  Button(
    onClick = onClick,
    enabled = isEnabled,
    modifier = modifier.width(width = 150.dp),
    shape = RoundedCornerShape(5.dp),
    border = BorderStroke(width = 2.dp, color = Color(0XFF54A3A9)),
    colors = ButtonDefaults.buttonColors(
      containerColor = Color.White,
    ),
  ) {
    Text(
      text = text,
      fontSize = 24.sp,
      color = Color(0XFF54A3A9),
    )
  }
}

@Preview
@Composable
private fun MathAppButtonPreview() {
  MathAppButton(
    text = "Ok",
    onClick = {},
    isEnabled = true,
  )
}