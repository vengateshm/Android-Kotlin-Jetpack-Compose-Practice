package dev.vengateshm.android_kotlin_compose_practice.multiple_backstacks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class MultipleBackStacksActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val rootNavController = rememberNavController()
                val navBackStackEntry by rootNavController.currentBackStackEntryAsState()

                Scaffold(
                    bottomBar = {
                        BottomAppBar {
                            botNavList.forEach { item ->
                                val isSelected =
                                    item.title.lowercase() ==
                                        navBackStackEntry?.destination?.route
                                BottomNavigationItem(
                                    label = { Text(text = item.title) },
                                    selected = isSelected,
                                    onClick = {
                                        rootNavController.navigate(item.title.lowercase()) {
                                            popUpTo(rootNavController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                            contentDescription = "${item.title} icon",
                                        )
                                    },
                                )
                            }
                        }
                    },
                ) { padding ->
                    NavHost(
                        modifier = Modifier.padding(padding),
                        navController = rootNavController,
                        startDestination = "home",
                    ) {
                        composable("home") {
                            SubNavHost(title = "home")
                        }
                        composable("chat") {
                            SubNavHost(title = "chat")
                        }
                        composable("settings") {
                            SubNavHost(title = "settings")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SubNavHost(title: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "${title}1") {
        for (i in 1..10) {
            composable("$title$i") {
                GenericScreen(
                    text = "$title$i",
                    onNextClicked = {
                        if (i < 10) {
                            navController.navigate("$title${i + 1}")
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun GenericScreen(
    text: String,
    onNextClicked: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = text)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onNextClicked() }) {
            Text(text = "Next")
        }
    }
}

data class BotNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

val botNavList =
    listOf(
        BotNavItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        BotNavItem(
            title = "Chat",
            selectedIcon = Icons.Filled.Chat,
            unselectedIcon = Icons.Outlined.Chat,
        ),
        BotNavItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
        ),
    )
