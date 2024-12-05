package dev.vengateshm.compose_material3.api_compose.navigation.navigation_without_one_time_event

import androidx.navigation.NavHostController

class Navigator1Impl : Navigator1 {
    private var navHostController: NavHostController? = null
    override fun setNavHostController(navHostController: NavHostController) {
        this.navHostController = navHostController
    }

    override fun navigate(route: Any) {
        this.navHostController?.navigate(route)
    }

    override fun popBackStack() {
        this.navHostController?.popBackStack()
    }

    override fun clear() {
        this.navHostController = null
    }
}