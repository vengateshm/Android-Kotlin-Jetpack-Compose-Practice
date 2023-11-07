package dev.vengateshm.android_kotlin_compose_practice.one_off_events

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class OneOffEventsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login-page") {
                    composable("login-page") {
                        val viewModel = viewModel<OneOffViewModel>()

                        /*val loginState = viewModel.loginState
                        LaunchedEffect(loginState.isLoggedIn) {
                            if (loginState.isLoggedIn) {
                                navController.navigate("profile-page")
                                viewModel.onNavigatedToProfile()
                            }
                        }*/

                        ObserveOneOffEvents(flow = viewModel.navigationEventChannel) { event ->
                            when (event) {
                                is NavigationEvent.NavigateToProfile -> navController.navigate("profile-page")
                                is NavigationEvent.CountEvent -> println(">> ${event.count}")
                            }
                        }

                        LoginPage(
                            state = viewModel.loginState,
                            onLoginClicked = viewModel::onLoginClicked
                        )
                    }
                    composable("profile-page") {
                        ProfilePage()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println(">> COUNT INTERRUPT")
    }
}

@Composable
fun <T> ObserveOneOffEvents(flow: Flow<T>, onEvent: (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(flow, lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            //flow.collect(onEvent)
            // To prevent event loss using channel use
            withContext(Dispatchers.Main.immediate){
                flow.collect(onEvent)
            }
        }
    }
}

@Composable
fun LoginPage(state: LoginState, onLoginClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onLoginClicked) {
            Text(text = "Login")
        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun ProfilePage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Profile")
    }
}