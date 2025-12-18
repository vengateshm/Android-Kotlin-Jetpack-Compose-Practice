package dev.vengateshm.navigation3_sample.bottom_sheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomSheet(
  modifier: Modifier = Modifier,
  data: String,
  onDismissed: () -> Unit,
) {
  val sheetState = rememberModalBottomSheetState()
  val scope = rememberCoroutineScope()
  fun dismiss() = scope.launch { sheetState.hide() }.invokeOnCompletion {
    onDismissed()
  }

  ModalBottomSheet(
    sheetState = sheetState,
    onDismissRequest = {
      dismiss()
    },
  ) {
    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      Text(text = data)
    }
  }
}