package dev.vengateshm.compose_material3.custom_ui.selectable_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SelectableList(
  modifier: Modifier = Modifier,
  items: List<DataItem>,
  selectedItem: DataItem? = null,
  onItemSelected: (dataItem: DataItem) -> Unit,
) {
  LazyColumn(
    modifier = modifier,
    contentPadding = PaddingValues(
      horizontal = 16.dp,
      vertical = 16.dp,
    ),
    verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    items(
      items = items,
      key = { item -> item.id },
    ) { item ->
      SelectableListItem(
        dataItem = item,
        selected = item == selectedItem,
        onItemSelected = onItemSelected,
      )
    }
  }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
private fun SelectableListPreview() {
  MaterialTheme {
    val items = listOf(
      DataItem(
        id = "0",
        title = "60 Months Variable",
        interestRate = 6.45,
        monthlyPaymentInCents = 77361,
      ),
      DataItem(
        id = "1",
        title = "6 Month Fixed",
        interestRate = 7.99,
        monthlyPaymentInCents = 66356,
      ),
      DataItem(
        id = "2",
        title = "1 Year Fixed Closed",
        interestRate = 6.49,
        monthlyPaymentInCents = 60301,
      ),
      DataItem(
        id = "3",
        title = "1 Year Fixed Open",
        interestRate = 6.95,
        monthlyPaymentInCents = 62129,
      ),
      DataItem(
        id = "4",
        title = "2 Year Fixed Open",
        interestRate = 5.95,
        monthlyPaymentInCents = 31065,
      ),
    )
    var selectedItem by remember { mutableStateOf<DataItem?>(null) }
    SelectableList(
      modifier = Modifier.fillMaxSize(),
      items = items,
      selectedItem = selectedItem,
      onItemSelected = {
        selectedItem = if (selectedItem == it) null else it
      },
    )
  }
}