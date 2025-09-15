package dev.vengateshm.compose_material3.custom_ui.carousel.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicator(
  pageCount: Int,
  currentPage: Int,
  onPageClick: (Int) -> Unit,
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    repeat(pageCount) { index ->
      val isSelected = index == currentPage

      Box(
        modifier = Modifier
          .size(8.dp)
          .clip(CircleShape)
          .background(color = if (isSelected) Color.Black else Color(0XFF555555))
          .clickable { onPageClick(index) }
          .padding(8.dp),
      )
    }
  }
}

@Preview
@Composable
fun PageIndicatorPreview() {
  PageIndicator(
    pageCount = 5,
    currentPage = 2,
    onPageClick = {},
  )
}
