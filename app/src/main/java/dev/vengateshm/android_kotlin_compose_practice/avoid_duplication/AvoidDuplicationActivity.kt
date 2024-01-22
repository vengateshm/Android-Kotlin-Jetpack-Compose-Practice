package dev.vengateshm.android_kotlin_compose_practice.avoid_duplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AvoidDuplicationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "mylist",
                ) {
                    composable("mylist") {
                        val viewModel = hiltViewModel<MyListViewModel>()
                        val state = viewModel.state.collectAsStateWithLifecycle()
                        BaseScreenComposable(
                            navController = navController,
                            viewModel = viewModel,
                        ) {
                            MyListComposable(
                                list = state.value.list,
                                onEvent = viewModel::onEvent,
                            )
                        }
                    }
                    composable("mydetail/{data}") {
                        it.arguments?.getString("data")?.let { value ->
                            MyDetailComposable(value)
                        }
                    }
                }
            }
        }
    }
}
