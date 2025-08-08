package dev.vengateshm.compose_material3.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@Composable
fun SnackBarSample(modifier: Modifier = Modifier) {
  val snackBarHostState = remember { SnackbarHostState() }
  val coroutineScope = rememberCoroutineScope()
  Scaffold(
    snackbarHost = {
      SnackbarHost(
        hostState = snackBarHostState,
      ) {
        Snackbar(
          snackbarData = it,
          containerColor = Color.LightGray,
          contentColor = Color.Black,
          actionColor = Color.DarkGray,
          dismissActionContentColor = Color.Black,
        )
      }
    },
  ) { paddingValues ->
    Column(
      modifier = modifier.padding(paddingValues = paddingValues),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      Button(
        onClick = {
          coroutineScope.launch {
            snackBarHostState.showSnackbar(
              message = "This is snack bar message",
              actionLabel = "Dismiss",
              withDismissAction = true,
              duration = SnackbarDuration.Indefinite,
            )
          }
        },
      ) {
        Text(text = "Show snack bar")
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SnackBarSamplePreview() {
  MaterialTheme {
    SnackBarSample()
  }
}