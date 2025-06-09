package dev.vengateshm.compose_material3.ui_concepts.bottom_navigation_with_nav_drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.vengateshm.compose_material3.ui_concepts.bottom_navigation_with_nav_drawer.AppDestination.HomeDestination
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit,
    onNavigateToSettings: () -> Unit,
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        scrimColor = Color.LightGray,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = CutCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    (1..5).forEach { index ->
                        NavigationDrawerItem(
                            label = { Text(text = "Menu $index") },
                            selected = false,
                            onClick = {
                                navController.navigate(AppDestination.HomeDestination(id = index.toString())) {
                                    popUpTo(0)
                                }
                                scope.launch { drawerState.close() }
                            },
                        )
                        if (index < 5) {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onLogout() }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = "Logout", modifier = Modifier.weight(1f))
                        Icon(imageVector = Icons.Default.Logout, contentDescription = "Logout")
                    }
                }
            }
        },
    ) {
        Scaffold(
            bottomBar = {
                val entry by navController.currentBackStackEntryAsState()
                val currentDestination = entry?.destination
                NavigationBar {
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.hasRoute(HomeDestination::class) } == true,
                        onClick = {
                            navController.navigate(HomeDestination(id = "1")) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                    )
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.hasRoute(AppDestination.AccountDestination::class) } == true,
                        onClick = {
                            navController.navigate(AppDestination.AccountDestination) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "Account") },
                        label = { Text("Account") },
                    )
                }
            },
        ) { contentPadding ->
            NavHost(
                modifier = Modifier.padding(contentPadding),
                navController = navController,
                startDestination = HomeDestination("1"),
            ) {
                composable<HomeDestination> { backStackEntry ->
                    val destination = backStackEntry.toRoute<HomeDestination>()
                    HomeScreen(id = destination.id)
                }
                composable<AppDestination.AccountDestination> { backStackEntry ->
                    AccountScreen(
                        onSettingsClick = onNavigateToSettings,
                    )
                }
            }
        }
    }
}