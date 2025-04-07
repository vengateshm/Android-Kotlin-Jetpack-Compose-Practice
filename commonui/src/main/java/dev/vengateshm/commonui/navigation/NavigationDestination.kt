package dev.vengateshm.commonui.navigation

import androidx.navigation.NavOptions

open class NavigationDestination(
    private val id: Int,
    private val navigationArguments: NavigationArguments? = null,
    private val navigationOptions: NavOptions? = null,
) {
    fun buildEvent(): NavigationEvent = NavigationEvent(id, navigationArguments, navigationOptions)
}