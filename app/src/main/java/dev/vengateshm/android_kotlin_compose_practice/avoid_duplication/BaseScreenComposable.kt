package dev.vengateshm.android_kotlin_compose_practice.avoid_duplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun <S, E> BaseScreenComposable(
    navController: NavController,
    viewModel: BaseViewModel<S, E>,
    content: @Composable () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Error -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = uiEvent.message,
                    )
                }

                is UiEvent.Navigate -> {
                    navController.navigate(uiEvent.route)
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        Box(modifier = Modifier.padding(it)) {
            content()
        }
    }
}
