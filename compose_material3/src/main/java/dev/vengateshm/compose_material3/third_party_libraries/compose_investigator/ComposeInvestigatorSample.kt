package dev.vengateshm.compose_material3.third_party_libraries.compose_investigator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ComposeInvestigatorSample(modifier: Modifier = Modifier) {
    var increment by remember { mutableIntStateOf(0) }
    var decrement by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { increment++ }) {
            Text(text = "Increment $increment")
        }
        Button(onClick = { decrement++ }) {
            Text(text = "Decrement $decrement")
        }
    }
}