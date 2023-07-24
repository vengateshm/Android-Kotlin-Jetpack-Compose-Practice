package dev.vengateshm.compose_material3.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBadge() {
    val badgeCount = remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxWidth()) {
        BadgedBox(
            badge = {
                Badge(containerColor = Color.Blue) {
                    Text(
                        text = "${badgeCount.value}",
                        color = Color.White
                    )
                }
            }) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        Button(onClick = { badgeCount.value++ }) {
            Text(text = "Increase badge count")
        }
    }
}