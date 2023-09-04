package dev.vengateshm.android_kotlin_compose_practice.apps.quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.vengateshm.android_kotlin_compose_practice.apps.quiz.screens.QuestionsScreen
import dev.vengateshm.android_kotlin_compose_practice.apps.quiz.screens.ResultScreen
import dev.vengateshm.android_kotlin_compose_practice.apps.quiz.screens.WelcomeScreen

class QuizAppMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: QuizViewModel by viewModels()

        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = QuizAppScreen.WelcomeScreen.route
                ) {
                    composable(route = QuizAppScreen.WelcomeScreen.route) {
                        WelcomeScreen(viewModel = viewModel,
                            onStartClicked = {
                                navController.navigate(QuizAppScreen.QuizScreen.route)
                            })
                    }
                    composable(route = QuizAppScreen.QuizScreen.route) {
                        QuestionsScreen(
                            viewModel = viewModel,
                            onFinishClicked = {
                                navController.navigate(QuizAppScreen.ResultScreen.route)
                            })
                    }
                    composable(route = QuizAppScreen.ResultScreen.route) {
                        ResultScreen(
                            viewModel = viewModel,
                            onStartAgain = {
                                navController.popBackStack(
                                    route = QuizAppScreen.WelcomeScreen.route,
                                    inclusive = false
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}