package dev.vengateshm.compose_material3.bottom_navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavigationSample() {
    val navController = rememberNavController()
    var selectedBottomTabIndex by remember {
        mutableIntStateOf(0)
    }
    Scaffold(
        bottomBar = {
            NavigationBar {
                BottomNavItem.bottomNavItems.forEachIndexed { index, bottomNavItem ->
                    NavigationBarItem(
                        selected = index == selectedBottomTabIndex,
                        onClick = { /*TODO*/ },
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
                                }) {
                                Icon(
                                    imageVector = bottomNavItem.icon,
                                    contentDescription = "${bottomNavItem.title} icon"
                                )
                            }
                        })
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues), navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {

}