package dev.vengateshm.compose_material3.api_compose.navigation.multi_module

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.vengateshm.samples.auth.navigation.AuthFeatureImpl
import dev.vengateshm.samples.common.navigation.NavDestGraph
import dev.vengateshm.samples.home.navigation.HomeFeatureImpl

@Composable
fun MultiModuleNavigationSample(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val graphs = remember {
        MultiModuleNavigationGraphs(
            authFeature = AuthFeatureImpl(),
            homeFeature = HomeFeatureImpl()
        )
    }
    NavHost(navController = navController, startDestination = NavDestGraph.AuthGraph) {
        graphs.authFeature.registerGraph(navController, this)
        graphs.homeFeature.registerGraph(navController, this)
    }
}