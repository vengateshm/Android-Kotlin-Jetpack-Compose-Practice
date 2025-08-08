package dev.vengateshm.compose_material3.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FancyDialog(modifier: Modifier = Modifier) {
  var showDialog by remember { mutableStateOf(false) }
  Box(modifier = Modifier.fillMaxSize()) {
    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      Button(onClick = { showDialog = true }) {
        Text(text = "Show Alert Dialog")
      }
    }
    if (showDialog) {
      BasicAlertDialog(
        onDismissRequest = {
          showDialog = false
        },
        properties = DialogProperties(),
      ) {
        Surface(
          modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.large),
          shape = MaterialTheme.shapes.large,
          tonalElevation = AlertDialogDefaults.TonalElevation,
        ) {
          Column(
            modifier = Modifier
              .background(Color(0xFF00E5FF))
              .padding(16.dp),
          ) {
            Text(text = "Title")
            Text(text = "This is dialog message")
            Row(horizontalArrangement = Arrangement.End) {
              Button(
                shape = RoundedCornerShape(16.dp),
                onClick = {
                  showDialog = false
                },
              ) { Text(text = "No") }
              Button(
                shape = RoundedCornerShape(16.dp),
                onClick = {
                  showDialog = false
                },
              ) { Text(text = "Yes") }
            }
          }
        }
      }
    }
  }
}