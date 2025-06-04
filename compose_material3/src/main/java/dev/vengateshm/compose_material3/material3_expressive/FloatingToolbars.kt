package dev.vengateshm.compose_material3.material3_expressive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Redo
import androidx.compose.material.icons.automirrored.filled.Undo
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FormatColorText
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.FloatingToolbarExitDirection
import androidx.compose.material3.FloatingToolbarHorizontalFabPosition
import androidx.compose.material3.FloatingToolbarVerticalFabPosition
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalFloatingToolbar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun VerticalFloatingToolbarSample(modifier: Modifier = Modifier) {
    val exitAlwaysScrollBehavior =
        FloatingToolbarDefaults.exitAlwaysScrollBehavior(exitDirection = FloatingToolbarExitDirection.End)
    val vibrantColors = FloatingToolbarDefaults.vibrantFloatingToolbarColors()
    var expanded by rememberSaveable { mutableStateOf(true) }
    Scaffold(
        modifier = Modifier.nestedScroll(exitAlwaysScrollBehavior),
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            LazyColumn(
                state = rememberLazyListState(),
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                val list = (0..75).map { it.toString() }
                items(count = list.size) {
                    Text(
                        text = "Item $it",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(color = Color.LightGray.copy(alpha = 0.5f))
                            .padding(8.dp),
                    )
                }
            }
            VerticalFloatingToolbar(
                expanded = expanded,
                floatingActionButton = {
                    FloatingToolbarDefaults.VibrantFloatingActionButton(
                        onClick = {
                            expanded = !expanded
                        },
                    ) {
                        if (expanded) {
                            Icon(Icons.Filled.Close, "Localized description")
                        } else {
                            Icon(Icons.Filled.Menu, "Localized description")
                        }
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp),
                colors = vibrantColors,
                contentPadding = FloatingToolbarDefaults.ContentPadding,
                scrollBehavior = exitAlwaysScrollBehavior,
                shape = FloatingToolbarDefaults.ContainerShape,
                floatingActionButtonPosition = FloatingToolbarVerticalFabPosition.Bottom,
                animationSpec = FloatingToolbarDefaults.animationSpec(),
                expandedShadowElevation = 4.dp,
                collapsedShadowElevation = FloatingToolbarDefaults.ContainerCollapsedElevationWithFab,
                content = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            Icons.AutoMirrored.Filled.Undo,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            Icons.AutoMirrored.Filled.Redo,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            Icons.Default.FormatColorText,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "Localized description",
                        )
                    }
                },
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun VerticalFloatingToolbarSamplePreview() {
    VerticalFloatingToolbarSample()
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HorizontalFloatingToolbarSample(modifier: Modifier = Modifier) {
    val exitAlwaysScrollBehavior =
        FloatingToolbarDefaults.exitAlwaysScrollBehavior(exitDirection = FloatingToolbarExitDirection.Bottom)
    val vibrantColors = FloatingToolbarDefaults.vibrantFloatingToolbarColors()
    var expanded by rememberSaveable { mutableStateOf(true) }
    Scaffold(
        modifier = Modifier.nestedScroll(exitAlwaysScrollBehavior),
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            LazyColumn(
                state = rememberLazyListState(),
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                val list = (0..75).map { it.toString() }
                items(count = list.size) {
                    Text(
                        text = "Item $it",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(color = Color.LightGray.copy(alpha = 0.5f))
                            .padding(8.dp),
                    )
                }
            }

            HorizontalFloatingToolbar(
                expanded = expanded,
                floatingActionButton = {
                    FloatingToolbarDefaults.VibrantFloatingActionButton(
                        onClick = {
                            expanded = !expanded
                        },
                    ) {
                        if (expanded) {
                            Icon(Icons.Filled.Close, "Localized description")
                        } else {
                            Icon(Icons.Filled.Menu, "Localized description")
                        }
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                colors = vibrantColors,
                contentPadding = FloatingToolbarDefaults.ContentPadding,
                scrollBehavior = exitAlwaysScrollBehavior,
                shape = FloatingToolbarDefaults.ContainerShape,
                floatingActionButtonPosition = FloatingToolbarHorizontalFabPosition.End,
                animationSpec = FloatingToolbarDefaults.animationSpec(),
                expandedShadowElevation = 4.dp,
                collapsedShadowElevation = FloatingToolbarDefaults.ContainerCollapsedElevationWithFab,
                content = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            Icons.AutoMirrored.Filled.Undo,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            Icons.AutoMirrored.Filled.Redo,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            Icons.Default.FormatColorText,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "Localized description",
                        )
                    }
                },
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HorizontalFloatingToolbarSamplePreview() {
    HorizontalFloatingToolbarSample()
}