package dev.vengateshm.compose_material3.accessibility

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex

@Composable
fun TraversalOrderSample1(modifier: Modifier = Modifier) {
  Column(modifier = Modifier) {
    Text(
      text = "1",
      modifier = Modifier.semantics {
        traversalIndex = 0f
      },
    )
    Text(
      text = "1",
      modifier = Modifier.semantics {
        traversalIndex = 2f
      },
    )
    Text(
      text = "1",
      modifier = Modifier.semantics {
        traversalIndex = 1f
      },
    )
  }
}