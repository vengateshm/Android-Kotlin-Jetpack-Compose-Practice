package dev.vengateshm.compose_material3.custom_ui.selectable_list

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Check(
  modifier: Modifier = Modifier,
  isChecked: Boolean,
) {
  val background by animateColorAsState(
    targetValue = if (isChecked) MaterialTheme.colorScheme.primary else Color.Transparent,
    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
  )

  val borderColor by animateColorAsState(
    targetValue = if (isChecked) MaterialTheme.colorScheme.primary else Color.Gray,
    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
  )

  Box(
    modifier = modifier
      .size(24.dp)
      .clip(CircleShape)
      .border(1.dp, borderColor, CircleShape)
      .background(background),
  ) {
    if (isChecked) {
      Icon(
        imageVector = Icons.Default.Check,
        contentDescription = null,
      )
    }
  }
}

@Preview
@Composable
private fun CheckPreview() {
  MaterialTheme {
    var isChecked by remember { mutableStateOf(false) }
    Row(
      modifier = Modifier.clickable {
        isChecked = !isChecked
      },
      horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      Check(isChecked = isChecked)
      Check(isChecked = !isChecked)
    }
  }
}