package dev.vengateshm.compose_material3.api_compose.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TypeSafeArgsInViewModel(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = C1,
    ) {
        composable<C1> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Button(
                    onClick = {
                        navController.navigate(C2(text = "From C1"))
                    },
                ) {
                    Text(text = "Go to C2")
                }
            }
        }
        composable<C2> {
            val viewModel = viewModel<C2ViewModel>()
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = viewModel.text.value)
            }
        }
    }
}

class C2ViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    var text = mutableStateOf("")

    init {
        text.value = savedStateHandle.toRoute<C2>().text
    }
}

@Serializable
data object C1

@Serializable
data class C2(val text: String)