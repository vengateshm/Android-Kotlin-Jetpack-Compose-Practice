package dev.vengateshm.navigation3_sample.navigate_with_result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ChangeSettingsScreen(
  resultStore: ResultStore,
  onSave: () -> Unit,
  modifier: Modifier = Modifier,
) {

  var text by remember { mutableStateOf("") }

  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {
    TextField(
      value = text,
      onValueChange = { text = it },
      modifier = Modifier.fillMaxWidth(),
    )
    Button(
      onClick = {
        resultStore.setResult("main_setting", text)
        onSave()
      },
    ) {
      Text(text = "Save")
    }
  }
}