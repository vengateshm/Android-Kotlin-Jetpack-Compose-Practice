package dev.vengateshm.compose_material3.api_compose.navigation.navigation_without_one_time_event

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

interface Navigator {
    val startDest: Dest1
    val navigationAction: Flow<NavigationAction>

    suspend fun navigate(
        dest1: Dest1,
        navOptions: NavOptionsBuilder.() -> Unit = {},
    )

    suspend fun navigateUp()
}

class DefaultNavigator(
    override val startDest: Dest1,
) : Navigator {

    // Multiple navigation actions can be emitted or replayed if we use shared flow when
    // app resumes and there could be possibility to handle action which are handle earlier
    // by other collectors
    //private val _navigationActions = MutableSharedFlow<NavigationAction>()
    private val _navigationActions = Channel<NavigationAction>() // 1 collector

    override val navigationAction = _navigationActions.receiveAsFlow()

    override suspend fun navigate(dest1: Dest1, navOptions: NavOptionsBuilder.() -> Unit) {
        _navigationActions.send(
            NavigationAction.NavigateTo(
                dest1 = dest1,
                navOptions = navOptions,
            ),
        )
    }

    override suspend fun navigateUp() {
        _navigationActions.send(NavigationAction.NavigateUp)
    }
}