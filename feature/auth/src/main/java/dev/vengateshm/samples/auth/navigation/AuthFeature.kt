package dev.vengateshm.samples.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.vengateshm.samples.auth.screens.AuthScreen
import dev.vengateshm.samples.common.navigation.Feature
import dev.vengateshm.samples.common.navigation.NavDest
import dev.vengateshm.samples.common.navigation.NavDestGraph

interface AuthFeature : Feature

class AuthFeatureImpl : AuthFeature {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<NavDestGraph.AuthGraph>(
            startDestination = NavDest.Auth
        ) {
            composable<NavDest.Auth> {
                AuthScreen {
                    navHostController.navigate(NavDest.Home)
                }
            }
        }
    }
}
