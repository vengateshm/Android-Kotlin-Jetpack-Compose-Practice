package dev.vengateshm.compose_material3.ui_concepts.bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetStickyView(modifier: Modifier = Modifier) {
  val sheetState = rememberModalBottomSheetState()
  val scope = rememberCoroutineScope()
  fun dismiss() = scope.launch { sheetState.hide() }.invokeOnCompletion {

  }
  ModalBottomSheet(
    onDismissRequest = {
      dismiss()
    },
    sheetState = sheetState,
  ) {
    Box(
      modifier = Modifier.fillMaxHeight(),
      contentAlignment = Alignment.BottomCenter,
    ) {
      LazyColumn() {
        items(100) {
          Text(
            text = "Item $it",
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
          )
        }
      }
      Column(
        modifier = Modifier
          .navigationBarsPadding()
          .offset {
            IntOffset(x = 0, y = -sheetState.requireOffset().toInt())
          }
          .fillMaxWidth()
          .background(color = Color.Gray)
          .padding(12.dp)
          .padding(bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Button(
          onClick = {
            dismiss()
          },
        ) {
          Text(text = "Close")
        }
      }
    }
  }
}