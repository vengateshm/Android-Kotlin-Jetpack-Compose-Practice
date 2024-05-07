package dev.vengateshm.compose_material3.ui_concepts.shared_element_transition

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedElementTransitionNavGraph(navController: NavHostController) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route
        ) {
            composable(route = Screens.Home.route) {
                HomeScreen(
                    animatedVisibilityScope = this@composable
                ) { imageId: Int ->
                    navController.navigate(Screens.Detail.passImageId(imageId))
                }
            }
            composable(
                route = Screens.Detail.route,
                arguments = listOf(navArgument(name = IMAGE_ID_ARG) {
                    type = NavType.IntType
                })
            ) {
                val imageId = it.arguments?.getInt(IMAGE_ID_ARG) ?: 1
                DetailScreen(
                    animatedVisibilityScope = this@composable,
                    imageId = imageId
                ) {
                    navController.popBackStack()
                }
            }
        }
    }
}