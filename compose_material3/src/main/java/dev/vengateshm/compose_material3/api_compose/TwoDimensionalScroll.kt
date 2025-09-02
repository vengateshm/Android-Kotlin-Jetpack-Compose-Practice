package dev.vengateshm.compose_material3.api_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollable2DState
import androidx.compose.foundation.gestures.scrollable2D
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun TwoDimensionalScroll(modifier: Modifier = Modifier) {
  val offset = remember { mutableStateOf(Offset.Zero) }
  Box(
    modifier = Modifier
      .height(200.dp)
      .width(200.dp)
      .background(Color.White)
      .padding(16.dp)
      .scrollable2D(
        state = rememberScrollable2DState { delta ->
          offset.value = offset.value + delta
          delta
        },
      )
      .background(Color.LightGray),
  ) {
    Text(
      text = "X=${offset.value.x.roundToInt()} Y=${offset.value.y.roundToInt()}",
      style = TextStyle(fontSize = 32.sp),
    )
  }
}

@Preview
@Composable
fun TwoDimensionalScrollPreview() {
  TwoDimensionalScroll()
}
