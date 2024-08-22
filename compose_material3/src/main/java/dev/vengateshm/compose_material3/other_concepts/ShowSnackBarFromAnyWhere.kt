package dev.vengateshm.compose_material3.other_concepts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.vengateshm.compose_material3.utils.ObserveAsEvents
import dev.vengateshm.compose_material3.utils.SnackBarAction
import dev.vengateshm.compose_material3.utils.SnackBarController
import dev.vengateshm.compose_material3.utils.SnackBarEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ShowSnackBarFromAnyWhere(modifier: Modifier = Modifier) {

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    ObserveAsEvents(
        flow = SnackBarController.events,
        key1 = snackBarHostState
    ) { event ->
        scope.launch {
            snackBarHostState.currentSnackbarData?.dismiss()

            val result = snackBarHostState.showSnackbar(
                message = event.message,
                actionLabel = event.action?.name,
                duration = SnackbarDuration.Short
            )
            if (result == SnackbarResult.ActionPerformed) {
                event.action?.action?.invoke()
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        modifier = Modifier.fillMaxSize()
    ) {
        val navController = rememberNavController()

        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = "FOO"
        ) {
            composable("FOO") {
                val viewModel = viewModel<ShowSnackBarFromAnyWhereViewModel>()
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Text(text = "Foo")
                        Button(onClick = {
                            scope.launch {
                                SnackBarController.sendEvent(
                                    event = SnackBarEvent(
                                        message = "Sent from FOO"
                                    )
                                )
                                delay(5000L)
                                viewModel.showSnackBar()
                            }
                        }) {
                            Text(text = "Show SnackBar")
                        }
                    }
                }
            }
            composable("BAR") {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Text(text = "Foo")
                        Button(onClick = {
                            scope.launch {
                                SnackBarController.sendEvent(
                                    event = SnackBarEvent(
                                        message = "Sent from BAR"
                                    )
                                )
                            }
                        }) {
                            Text(text = "Show SnackBar")
                        }
                    }
                }
            }
        }
    }
}

class ShowSnackBarFromAnyWhereViewModel : ViewModel() {
    fun showSnackBar() {
        viewModelScope.launch {
            SnackBarController.sendEvent(
                event = SnackBarEvent(
                    message = "Sent from ViewModel",
                    action = SnackBarAction(
                        name = "Send again",
                        action = {
                            SnackBarController.sendEvent(
                                event = SnackBarEvent(
                                    message = "Sent again from ViewModel",
                                )
                            )
                        }
                    )
                )
            )
        }
    }
}