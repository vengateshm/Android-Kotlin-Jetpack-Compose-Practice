package dev.vengateshm.compose_material3.third_party_libraries.koin_scope

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    onNext: () -> Unit,
    viewModel: AuthScreenViewModel,
) {
    var loggedIn by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        delay(5000L)
        loggedIn = true
        viewModel.login()
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Auth${if (loggedIn) "\nLogged in" else ""}")
        Button(onClick = { onNext() }) {
            Text(text = "Next")
        }
    }
}

class AuthScreenViewModel : ViewModel(), KoinComponent {

    //private val landingRepository: LandingRepository by inject(named("LoginLogoutScope"))

    init {
        //landingRepository.getLandingData()
    }

    fun login() {
        val scope = getKoin().createScope("login_logout_session", named("LoginLogoutScope"))
    }

    fun logout() {
        getKoin().getScopeOrNull("login_logout_session")?.close()
    }
}