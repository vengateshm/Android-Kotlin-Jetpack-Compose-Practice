package dev.vengateshm.navigation3_sample.list_detail_screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Nav3ListScreen(
  modifier: Modifier = Modifier,
  onNavigateToDetail: (String) -> Unit,
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp),
  ) {
    items(100) {
      ListItem(
        modifier = Modifier.clickable {
          onNavigateToDetail("Item $it")
        },
        headlineContent = {
          Text(text = "Item $it")
        },
      )
    }
  }
}

@Preview
@Composable
private fun Nav3ListScreenPreview() {
  Nav3ListScreen(
    onNavigateToDetail = {},
  )
}