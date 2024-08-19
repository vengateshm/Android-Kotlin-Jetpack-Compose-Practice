package dev.vengateshm.compose_material3.window

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WindowInsetsSample(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
        //.safeContentPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsTopHeight(
                    insets = WindowInsets.statusBars
                )
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFF3E8C4),
                            Color(0xFFF1CA48),
                        )
                    )
                )
        )
        /*Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {}
        ) {
            Text(
                text = "Button At Bottom",
            )
        }*/
//        LazyColumnWithWindowInsetsSystemBarsPadding()
        LazyColumnWithImePadding()
    }
}

@Composable
fun LazyColumnWithWindowInsetsSystemBarsPadding(modifier: Modifier = Modifier) {
    LazyColumn(
        contentPadding = WindowInsets.systemBars.asPaddingValues()
    ) {
        items(20, key = { it }) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Item ${it + 1}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFFB6E3F7),
                                    Color(0xFF48BEF4),
                                )
                            )
                        )
                        .padding(32.dp)
                )
            }
        }
    }
}

@Composable
fun LazyColumnWithImePadding(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = Modifier.imePadding()
    ) {
        items(20, key = { it }) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Item ${it + 1}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFFB6E3F7),
                                    Color(0xFF48BEF4),
                                )
                            )
                        )
                        .padding(32.dp)
                )
            }
        }
        item {
            var text by remember {
                mutableStateOf("")
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = text,
                onValueChange = { text = it }
            )
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsBottomHeight(
                        insets = WindowInsets.systemBars
                    )
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFF4B6B1),
                                Color(0xFFED665C),
                            )
                        )
                    )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WindowInsetsSample1(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                windowInsets = WindowInsets(0, 0, 0, 0),
                title = { Text(text = "App Bar") })
        },
        bottomBar = {
            BottomAppBar(
                windowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 8.dp),
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Check, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Localized description")
                    }
                })
        }) {
        println(it)
    }
}