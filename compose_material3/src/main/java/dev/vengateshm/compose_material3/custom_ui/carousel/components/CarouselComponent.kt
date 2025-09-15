package dev.vengateshm.compose_material3.custom_ui.carousel.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.custom_ui.carousel.models.CarouselItem
import dev.vengateshm.compose_material3.custom_ui.carousel.models.carouselItems
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselComponent(
  items: List<CarouselItem>,
  onPageChange: (Int) -> Unit,
  modifier: Modifier = Modifier,
) {
  val pagerState = rememberPagerState(
    initialPage = 0,
    pageCount = { items.size },
  )

  val coroutineScope = rememberCoroutineScope()
  var expandedCardId by remember { mutableIntStateOf(-1) }

  Column(
    modifier = modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    HorizontalPager(
      state = pagerState,
    ) { pageIndex ->
      val item = items[pageIndex]
      ExpandableCarouselItem(
        item = item,
        isExpanded = expandedCardId == item.id,
        onExpandToggle = {
          expandedCardId = if (expandedCardId == item.id) -1 else item.id
        },
        modifier = Modifier.padding(horizontal = 8.dp),
      )
    }

    Spacer(modifier = Modifier
      .fillMaxWidth()
      .height(8.dp))

    PageIndicator(
      pageCount = items.size,
      currentPage = pagerState.currentPage,
      onPageClick = { pageIndex ->
        coroutineScope.launch {
          pagerState.animateScrollToPage(pageIndex)
        }
      },
      modifier = Modifier.padding(2.dp),
    )
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CarouselComponentPreview() {
  MaterialTheme {
    Surface(
      color = Color.White,
    ) {
      CarouselComponent(
        items = carouselItems,
        onPageChange = {},
      )
    }
  }
}