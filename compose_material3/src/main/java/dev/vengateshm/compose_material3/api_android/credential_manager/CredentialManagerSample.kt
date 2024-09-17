package dev.vengateshm.compose_material3.api_android.credential_manager

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data object LoginRoute

@Serializable
data class LoggedInRoute(val username: String)

@Composable
fun CredentialManagerSample(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LoginRoute,
    ) {
        composable<LoginRoute> {
            val viewModel = viewModel<LoginViewModel>()
            LoginScreen(
                state = viewModel.state,
                onAction = viewModel::onAction,
                onLoggedIn = {
                    navController.navigate(LoggedInRoute(it)) {
                        popUpTo(LoginRoute) {
                            inclusive = true
                        }
                    }
                },
            )
        }
        composable<LoggedInRoute> {
            val username = it.toRoute<LoggedInRoute>().username
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Logged in as : $username")
            }
        }
    }
}

data class LoginState(
    val loggedInUser: String? = null,
    val username: String = "user",
    val password: String = "pass",
    val errorMessage: String? = null,
    val isRegister: Boolean = false,
)

sealed interface LoginAction {
    data class OnSignIn(val signInResult: SignInResult) : LoginAction
    data class OnSignUp(val signUpResult: SignUpResult) : LoginAction
    data class OnUsernameChange(val username: String) : LoginAction
    data class OnPasswordChange(val password: String) : LoginAction
    data object OnToggleIsRegister : LoginAction
}


@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
    onLoggedIn: (String) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val credentialManager = remember { UserCredentialsManager(context as ComponentActivity) }

    LaunchedEffect(true) {
        val result = credentialManager.signIn()
        onAction(LoginAction.OnSignIn(result))
    }

    LaunchedEffect(key1 = state.loggedInUser) {
        if (state.loggedInUser != null) {
            onLoggedIn(state.loggedInUser)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Username") },
            value = state.username,
            onValueChange = {
                onAction(LoginAction.OnUsernameChange(it))
            },
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Password") },
            value = state.password,
            onValueChange = {
                onAction(LoginAction.OnPasswordChange(it))
            },
        )
        Row {
            Text(text = "Register")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = state.isRegister,
                onCheckedChange = {
                    onAction(LoginAction.OnToggleIsRegister)
                },
            )
        }
        if (state.errorMessage != null) {
            Text(
                text = state.errorMessage,
                color = MaterialTheme.colorScheme.error,
            )
        }
        Button(
            onClick = {
                coroutineScope.launch {
                    if (state.isRegister) {
                        val signUpResult = credentialManager.signUp(
                            username = state.username,
                            password = state.password,
                        )
                        onAction(LoginAction.OnSignUp(signUpResult))
                    }
                }
            },
        ) {
            Text(text = if (state.isRegister) "Register" else "Login")
        }
    }
}


class LoginViewModel : ViewModel() {
    var state: LoginState by mutableStateOf(LoginState())
        private set

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnPasswordChange -> {
                state = state.copy(password = action.password)
            }

            is LoginAction.OnSignUp -> {
                when (action.signUpResult) {
                    SignUpResult.Cancelled -> {
                        state = state.copy(
                            errorMessage = "Sign up cancelled",
                        )
                    }

                    SignUpResult.Failure -> {
                        state = state.copy(
                            errorMessage = "Sign up failed",
                        )
                    }

                    is SignUpResult.Success -> {
                        state = state.copy(
                            loggedInUser = action.signUpResult.username,
                        )
                    }
                }
            }

            LoginAction.OnToggleIsRegister -> {
                state = state.copy(isRegister = !state.isRegister)
            }

            is LoginAction.OnUsernameChange -> {
                state = state.copy(username = action.username)
            }

            is LoginAction.OnSignIn -> {
                when (action.signInResult) {
                    SignInResult.Cancelled -> {
                        state = state.copy(
                            errorMessage = "Sign in cancelled",
                        )
                    }

                    SignInResult.Failure -> {
                        state = state.copy(
                            errorMessage = "Sign in cancelled",
                        )
                    }

                    SignInResult.NoCredentials -> {
                        state = state.copy(
                            errorMessage = "No credentials set",
                        )
                    }

                    is SignInResult.Success -> {
                        state = state.copy(
                            loggedInUser = action.signInResult.username,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CredentialManagerSamplePreview() {
    MaterialTheme {
        Surface {
            CredentialManagerSample()
        }
    }
}