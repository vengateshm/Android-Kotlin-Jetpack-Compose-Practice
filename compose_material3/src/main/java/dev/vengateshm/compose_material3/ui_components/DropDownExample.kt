package dev.vengateshm.compose_material3.ui_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DropDownExample(modifier: Modifier = Modifier) {
  val menuItems = listOf("Italy", "Germany", "England")
  val (selectedItem, onSelect) = remember { mutableStateOf("Select") }
  var showDropDown by remember { mutableStateOf(false) }

  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {
    Box {
      Row(
        modifier = Modifier.clickable {
          showDropDown = true
        },
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Text(text = selectedItem)
        Icon(
          imageVector = Icons.Default.ArrowDropDown,
          contentDescription = null,
        )
      }
      DropdownMenu(
        expanded = showDropDown,
        onDismissRequest = {
          showDropDown = false
        },
      ) {
        menuItems.forEach { menuItem ->
          DropdownMenuItem(
            text = {
              Text(text = menuItem)
            },
            onClick = {
              onSelect(menuItem)
              showDropDown = false
            },
          )
        }
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DropDownExamplePreview() {
  MaterialTheme {
    DropDownExample()
  }
}