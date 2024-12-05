package dev.vengateshm.compose_material3.api_compose.navigation.navigation_without_one_time_event

import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import org.koin.compose.koinInject

@Composable
fun Navigator2Sample(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navigator = koinInject<Navigator2>()
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Main) {
            navigator.events.collectLatest { event ->
                when (event) {
                    is NavigationEvent.Navigate -> navigator.navigate(event.route)
                    NavigationEvent.PopBackStack -> navigator.popBackStack()
                }
            }
        }
    }
    NavHost(
        navController = navController,
        startDestination = Dest1.Add,
        modifier = Modifier.safeContentPadding(),
    ) {
        composable<Dest1.Add> {
            val viewModel = koinInject<AddScreenViewModel>()
            AddScreen(viewModel)
        }
        composable<Dest1.Edit> {
            val viewModel = koinInject<EditScreenViewModel>()
            EditScreen(viewModel)
        }
    }
}