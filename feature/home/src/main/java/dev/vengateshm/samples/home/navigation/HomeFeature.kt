package dev.vengateshm.samples.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.vengateshm.samples.common.navigation.Feature
import dev.vengateshm.samples.common.navigation.NavDest
import dev.vengateshm.samples.common.navigation.NavDestGraph
import dev.vengateshm.samples.home.screens.HomeScreen

interface HomeFeature : Feature

class HomeFeatureImpl : HomeFeature {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<NavDestGraph.HomeGraph>(startDestination = NavDest.Home) {
            composable<NavDest.Home> {
                HomeScreen { }
            }
        }
    }
}
