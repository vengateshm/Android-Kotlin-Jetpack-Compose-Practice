package dev.vengateshm.compose_material3.material3_expressive

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.SplitButtonDefaults
import androidx.compose.material3.SplitButtonLayout
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SplitButtonsSample(modifier: Modifier = Modifier) {
  var isClicked by remember { mutableStateOf(false) }
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    SplitButtonLayout(
      leadingButton = {
        SplitButtonDefaults.LeadingButton(
          onClick = {},
        ) {
          Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = null,
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(text = "Edit")
        }
      },
      trailingButton = {
        SplitButtonDefaults.TrailingButton(
          onClick = {
            isClicked = !isClicked
          },
        ) {
          Icon(
            imageVector = if (isClicked) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
            contentDescription = null,
          )
        }
        DropdownMenu(
          expanded = isClicked,
          onDismissRequest = {
            isClicked = false
          },
        ) {
          for (i in 1..3) {
            DropdownMenuItem(
              text = { Text(text = "Item $i") },
              onClick = {},
            )
          }
        }
      },
    )
  }
}

@Preview
@Composable
private fun SplitButtonsSamplePreview() {
  SplitButtonsSample()
}