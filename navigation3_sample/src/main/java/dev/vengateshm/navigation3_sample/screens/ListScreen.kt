package dev.vengateshm.navigation3_sample.screens

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
fun ListScreen(
  modifier: Modifier = Modifier,
  onItemClick: (String) -> Unit,
) {
  LazyColumn(
    modifier = modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp),
  ) {
    items(100) {
      ListItem(
        modifier = Modifier.clickable {
          onItemClick("List item $it")
        },
        headlineContent = {
          Text(text = "List item $it")
        },
      )
    }
  }
}

@Preview
@Composable
private fun ListScreenPreview() {
  ListScreen(
    onItemClick = {},
  )
}