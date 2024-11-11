package dev.vengateshm.compose_material3.third_party_libraries.koin_scope

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel

@Composable
fun EntryScreen(
    modifier: Modifier = Modifier,
    onNext: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Entry")
        Button(onClick = { onNext() }) {
            Text(text = "Next")
        }
    }
}

class EntryScreenViewModel : ViewModel() {

}