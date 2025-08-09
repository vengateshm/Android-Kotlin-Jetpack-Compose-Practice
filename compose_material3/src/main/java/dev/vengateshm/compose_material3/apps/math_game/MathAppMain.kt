package dev.vengateshm.compose_material3.apps.math_game

import androidx.activity.compose.LocalActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MathAppMain(modifier: Modifier = Modifier) {
  val navController = rememberNavController()
  MathAppNavigation(
    navController = navController,
  )
}

@Composable
fun MathAppNavigation(
  modifier: Modifier = Modifier,
  navController: NavHostController,
) {

  val activity = LocalActivity.current

  NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = MathAppScreen.HOME.name,
  ) {
    composable(route = MathAppScreen.HOME.name) {
      Home(
        goToGameScreen = { operationType ->
          navController.navigate(route = "${MathAppScreen.GAME.name}/${operationType.name}")
        },
      )
    }
    composable(
      route = "${MathAppScreen.GAME.name}/{operationType}",
      arguments = listOf(
        navArgument("operationType") {
          type = NavType.StringType
        },
      ),
    ) { navBackStackEntry ->
      val operationType = navBackStackEntry.arguments?.getString("operationType")
      Game(
        modifier = modifier,
        operationType = OperationType.valueOf(operationType ?: OperationType.ADDITION.name),
        onBackClick = { navController.popBackStack() },
        goToResultScreen = { score ->
          navController.navigate(
            route = "${MathAppScreen.RESULT.name}/${score}",
          ) {
            popUpTo(route = MathAppScreen.HOME.name) {
              inclusive = false
            }
          }
        },
      )
    }
    composable(
      route = "${MathAppScreen.RESULT.name}/{score}",
      arguments = listOf(
        navArgument("score") {
          type = NavType.IntType
        },
      ),
    ) { navBackStackEntry ->
      val score = navBackStackEntry.arguments?.getInt("score")
      Result(
        score = score ?: 0,
        onPlayAgainClick = {
          navController.popBackStack(route = MathAppScreen.HOME.name, inclusive = false)
        },
        onExitClick = {
          activity?.finish()
        },
      )
    }
  }
}

@Preview
@Composable
private fun MathAppMainPreview() {
  MathAppMain()
}