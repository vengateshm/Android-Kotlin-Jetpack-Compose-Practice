package dev.vengateshm.compose_material3.api_compose

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WindowInsetsSample(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Hello World") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray,
                ),
            )
        },
        bottomBar = {
            BottomAppBar {

            }
        },
        contentWindowInsets = WindowInsets.safeContent,
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
        ) {
            items(100) {
                Text("I'm item $it", modifier = modifier)
            }
        }
    }
}