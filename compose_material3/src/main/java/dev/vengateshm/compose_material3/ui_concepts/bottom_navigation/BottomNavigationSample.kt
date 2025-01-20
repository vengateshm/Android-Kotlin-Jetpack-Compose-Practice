package dev.vengateshm.compose_material3.ui_concepts.bottom_navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavigationSample() {
    val navController = rememberNavController()
    /*val selectedBottomTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }*/

    val currentBackStackEntry by navController.currentBackStackEntryFlow.collectAsStateWithLifecycle(
        initialValue = null,
    )
    var selectedBottomTabIndex = when (currentBackStackEntry?.destination?.route) {
        "home" -> 0
        "category" -> 1
        "notification" -> 2
        "account" -> 3
        else -> -1
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                BottomNavItem.bottomNavItems.forEachIndexed { index, bottomNavItem ->
                    NavigationBarItem(
                        selected = index == selectedBottomTabIndex,
                        onClick = {
                            selectedBottomTabIndex = index
                            //navController.popBackStack() // Close app when clicked back button when in any of the bottom nav screen
                            when (index) {
                                0 -> navController.navigate("home")
                                1 -> navController.navigate("category")
                                2 -> navController.navigate("notification")
                                3 -> navController.navigate("account")
                            }
                        },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (bottomNavItem.badgeCount != 0) {
                                        Badge {
                                            Text(text = bottomNavItem.badgeCount.toString())
                                        }
                                    } else if (bottomNavItem.hasNews) {
                                        Badge()
                                    }
                                },
                            ) {
                                Icon(
                                    imageVector = bottomNavItem.icon,
                                    contentDescription = "${bottomNavItem.title} icon",
                                )
                            }
                        },
                    )
                }
            }
        },
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues), navController = navController,
            startDestination = "home",
        ) {
            composable("home") {
                HomeScreen(
                    onShowErrorDialog = {
                        val errMsg = "Error loading list"
                        navController.navigate("error_dialog/$errMsg")
                    },
                )
            }
            dialog("error_dialog/{err_msg}") {
                it.arguments?.getString("err_msg")?.let { errMsg ->
                    ErrorDialog(
                        onDismissRequest = {
                            navController.popBackStack()
                        },
                        message = errMsg,
                        btnText = "OK",
                    )
                }
            }
            composable("category") {
                CategoryScreen()
            }
            composable("notification") {
                NotificationScreen()
            }
            composable("account") {
                AccountScreen()
            }
        }
    }
}

@Composable
fun HomeScreen(onShowErrorDialog: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = {
                onShowErrorDialog()
            },
        ) {
            Text(text = "Show error dialog")
        }
    }
}

@Composable
fun ErrorDialog(onDismissRequest: () -> Unit, message: String, btnText: String) {
    AlertDialog(
        text = {
            Text(text = message)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                },
            ) {
                Text(text = btnText)
            }
        },
    )
}

@Composable
fun CategoryScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Category")
    }
}

@Composable
fun NotificationScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Notification")
    }
}

@Composable
fun AccountScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Account")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun BottomNavigationSamplePreview() {
    BottomNavigationSample()
}