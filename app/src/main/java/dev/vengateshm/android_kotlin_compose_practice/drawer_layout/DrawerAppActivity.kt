package dev.vengateshm.android_kotlin_compose_practice.drawer_layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class DrawerAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DrawerAppContent()
        }
    }
}

@Composable
fun DrawerAppContent() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var currentScreen by remember {
        mutableStateOf(DrawerAppScreen.Home)
    }
    val coroutineScope = rememberCoroutineScope()

    ModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            DrawerContent(
                drawerAppScreen = currentScreen,
                onDrawerItemClick = {
                    currentScreen = it
                },
                onDrawerClose = {
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }
            )
        },
        content = {
            BodyContent(
                drawerAppScreen = currentScreen,
                openDrawer = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                })
        })
}

@Composable
fun DrawerContent(
    drawerAppScreen: DrawerAppScreen,
    onDrawerItemClick: (DrawerAppScreen) -> Unit,
    onDrawerClose: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        for (index in DrawerAppScreen.values().indices) {
            val screen = getScreenBasedOnIndex(index)
            Column(
                modifier = Modifier.clickable(
                    onClick = {
                        onDrawerClose()
                        onDrawerItemClick(getScreenBasedOnIndex(index))
                    }
                ),
                content = {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = if (drawerAppScreen == screen) {
                            MaterialTheme.colors.secondary
                        } else {
                            MaterialTheme.colors.surface
                        }
                    ) {
                        Text(
                            text = screen.name,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                })
        }
    }
}

fun getScreenBasedOnIndex(index: Int) = when (index) {
    0 -> DrawerAppScreen.Home
    1 -> DrawerAppScreen.Settings
    2 -> DrawerAppScreen.Help
    else -> DrawerAppScreen.Home
}

@Composable
fun BodyContent(drawerAppScreen: DrawerAppScreen, openDrawer: () -> Unit) {
    when (drawerAppScreen) {
        DrawerAppScreen.Home -> {
            Home(openDrawer = openDrawer)
        }

        DrawerAppScreen.Settings -> {
            Settings(openDrawer = openDrawer)
        }

        DrawerAppScreen.Help -> {
            Help(openDrawer = openDrawer)
        }
    }
}

enum class DrawerAppScreen {
    Home,
    Settings,
    Help
}

@Composable
fun Home(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text("Home")
            },
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
            }
        )
        Surface(
            color = Color(0xFFffd7d7.toInt()),
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Text(text = "Home")
                }
            )
        }
    }
}

@Composable
fun Settings(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Settings") },
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
            }
        )
        Surface(
            color = Color(0xFFffd7d7.toInt()),
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Text(text = "Settings")
                }
            )
        }
    }
}

@Composable
fun Help(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Help") },
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
            }
        )
        Surface(
            color = Color(0xFFffd7d7.toInt()),
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Text(text = "Help")
                }
            )
        }
    }
}

@Preview
@Composable
fun DrawerAppComponentPreview() {
    DrawerAppContent()
}
