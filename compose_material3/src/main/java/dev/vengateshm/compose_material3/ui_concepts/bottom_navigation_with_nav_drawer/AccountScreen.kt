package dev.vengateshm.compose_material3.ui_concepts.bottom_navigation_with_nav_drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    onSettingsClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Account Screen", modifier = modifier)
        Button(onClick = onSettingsClick) {
            Text("Go To Settings")
        }
    }
}