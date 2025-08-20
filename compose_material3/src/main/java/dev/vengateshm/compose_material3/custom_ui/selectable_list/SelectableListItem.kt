package dev.vengateshm.compose_material3.custom_ui.selectable_list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.UUID

@Composable
fun SelectableListItem(
  modifier: Modifier = Modifier,
  dataItem: DataItem,
  selected: Boolean,
  onItemSelected: (DataItem) -> Unit,
) {
  Column(modifier = modifier) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp))
        .clickable { onItemSelected(dataItem) }
        .padding(16.dp),
      horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      Check(isChecked = selected)
      Column(
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
      ) {
        Text(text = dataItem.title)
        Row(modifier = Modifier.fillMaxWidth()) {
          Column(
            modifier = Modifier.weight(weight = 1f),
          ) {
            Text(
              text = "Interest Rate",
              style = MaterialTheme.typography.labelSmall,
            )
            Text(text = "${dataItem.interestRate}")
          }
          Column(
            modifier = Modifier.weight(weight = 1f),
          ) {
            Text(
              text = "Monthly Payment",
              style = MaterialTheme.typography.labelSmall,
            )
            Text(text = dataItem.monthlyPaymentFormatted)
          }
        }
      }
    }
  }
}

@Preview
@Composable
private fun SelectableListItemPreview() {
  MaterialTheme {
    Column(
      modifier = Modifier
        .background(MaterialTheme.colorScheme.background)
        .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      SelectableListItem(
        dataItem = DataItem(
          id = UUID.randomUUID().toString(),
          title = "60 Months Varable",
          interestRate = 6.45,
          monthlyPaymentInCents = 77361,
        ),
        selected = false,
        onItemSelected = {},
      )
      SelectableListItem(
        dataItem = DataItem(
          id = UUID.randomUUID().toString(),
          title = "1 Year Fixed",
          interestRate = 5.67,
          monthlyPaymentInCents = 70236,
        ),
        selected = true,
        onItemSelected = {},
      )
    }
  }
}