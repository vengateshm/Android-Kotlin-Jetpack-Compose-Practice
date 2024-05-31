package dev.vengateshm.compose_material3.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.vengateshm.compose_material3.R

@Composable
fun ElevatedButtonSample(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ElevatedButton(
            onClick = {}
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_coffee_24),
                contentDescription = null
            )
            Text("Coffee")
        }
    }
}

@Preview
@Composable
private fun ElevatedButtonSamplePreview() {
    ElevatedButtonSample()
}