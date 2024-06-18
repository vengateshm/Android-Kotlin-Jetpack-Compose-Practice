package dev.vengateshm.compose_material3.ui_concepts.bottom_sheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ModalBottomSheetSample(
    modifier: Modifier = Modifier,
    viewModel: BottomSheetViewModel = viewModel()
) {
    val state = viewModel.state
    if (state.show) {
        BottomSheet(state.data) {
            viewModel.dismissDialog()
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            viewModel.getCountries()
        }) {
            Text(text = "Show Modal BottomSheet")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(data: List<Pair<String, String>>, onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        CountryList(data = data)
    }
}

@Composable
fun CountryList(
    data: List<Pair<String, String>>
) {
    val bottomPadding =
        WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding().value.toInt()
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(height = 400.dp)
            .padding(bottom = bottomPadding.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .height(height = 400.dp)
        ) {
            items(data) { (country, flag) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                ) {
                    Text(
                        text = flag,
                        modifier = Modifier.padding(end = 20.dp)
                    )
                    Text(text = country)
                }
            }
        }
    }
}