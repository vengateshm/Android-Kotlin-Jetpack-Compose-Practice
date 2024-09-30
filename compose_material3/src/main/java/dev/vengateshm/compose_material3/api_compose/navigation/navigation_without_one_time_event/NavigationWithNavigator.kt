package dev.vengateshm.compose_material3.api_compose.navigation.navigation_without_one_time_event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.vengateshm.compose_material3.utils.ObserveAsEvents
import org.koin.compose.koinInject
import java.util.UUID

@Composable
fun NavigationWithNavigator(modifier: Modifier = Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val navController = rememberNavController()
        val navigator = koinInject<Navigator>()

        ObserveAsEvents(flow = navigator.navigationAction) { action ->
            when (action) {
                is NavigationAction.NavigateTo -> navController.navigate(
                    action.dest1,
                ) {
                    action.navOptions(this)
                }

                NavigationAction.NavigateUp -> navController.navigateUp()
            }
        }

        NavHost(
            navController = navController,
            startDestination = navigator.startDest,
            modifier = Modifier.padding(innerPadding),
        ) {
            navigation<Dest1.AuthGraph>(
                startDestination = Dest1.Login,
            ) {
                composable<Dest1.Login> {
                    val viewModel = koinInject<LoginViewModel1>()
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Button(onClick = { viewModel.login() }) {
                            Text(text = "Login")
                        }
                    }
                }
            }
            navigation<Dest1.HomeGraph>(
                startDestination = Dest1.Home,
            ) {
                composable<Dest1.Home> {
                    val viewModel = koinInject<HomeViewModel1>()
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Button(
                            onClick = {
                                viewModel.navigateToDetail(
                                    UUID.randomUUID().toString(),
                                )
                            },
                        ) {
                            Text(text = "Go to Detail")
                        }
                    }
                }
                composable<Dest1.Detail> {
                    val viewModel = koinInject<DetailViewModel1>()
                    val args = it.toRoute<Dest1.Detail>()
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = "ID ${args.hashCode()}")
                        Button(onClick = { viewModel.goBack() }) {
                            Text(text = "Go back")
                        }
                    }
                }
            }
        }
    }
}