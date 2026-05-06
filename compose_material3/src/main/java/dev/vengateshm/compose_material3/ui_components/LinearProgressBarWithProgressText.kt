package dev.vengateshm.compose_material3.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomProgressBar(
  modifier: Modifier = Modifier,
  width: Dp,
  backgroundColor: Color,
  foregroundColor: Brush,
  percent: Int,
  showText: Boolean = true,
) {
  Box(
    modifier = modifier
      .background(color = backgroundColor)
      .width(width = width)
      .fillMaxHeight(),
  ) {
    Box(
      modifier = modifier
        .background(brush = foregroundColor)
        .width(width = width * percent / 100),
    )
    Text(
      text = "$percent %",
      modifier = Modifier
        .align(alignment = Alignment.Center),
      fontSize = 14.sp,
      textAlign = TextAlign.Center,
      fontWeight = FontWeight.Bold,
      color = Color.White,
    )
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CustomProgressBarPreview() {
  CustomProgressBar(
    modifier = Modifier
      .clip(RoundedCornerShape(100))
      .height(25.dp),
    width = 300.dp,
    backgroundColor = Color.LightGray,
    foregroundColor = Brush.horizontalGradient(listOf(Color(0XFF2050FD), Color(0XFF1AACFB))),
    percent = (0.78 * 100).toInt(),
  )
}