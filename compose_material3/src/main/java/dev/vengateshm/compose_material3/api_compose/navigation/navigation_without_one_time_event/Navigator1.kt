package dev.vengateshm.compose_material3.api_compose.navigation.navigation_without_one_time_event

import androidx.navigation.NavHostController

interface Navigator1 {
    fun setNavHostController(navHostController: NavHostController) // UI component and not a good practice use channel or mutable shared flow
    fun navigate(route: Any)
    fun popBackStack()
    fun clear()
}