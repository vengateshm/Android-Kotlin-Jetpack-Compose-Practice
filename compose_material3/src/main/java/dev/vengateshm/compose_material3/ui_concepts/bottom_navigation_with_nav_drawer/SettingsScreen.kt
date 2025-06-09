package dev.vengateshm.compose_material3.ui_concepts.bottom_navigation_with_nav_drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onCloseSettings: () -> Unit,
) {
    Column {
        IconButton(
            onClick = onCloseSettings,
        ) {
            Icon(Icons.Default.Close, contentDescription = null)
        }
        Column {
            Text("Settings Screen", modifier = modifier)
        }
    }
}