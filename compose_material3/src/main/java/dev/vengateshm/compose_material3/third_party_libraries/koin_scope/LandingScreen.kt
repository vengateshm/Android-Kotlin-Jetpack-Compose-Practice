package dev.vengateshm.compose_material3.third_party_libraries.koin_scope

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    viewModel: LandingScreenViewModel,
) {
    var landingData by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = Unit) {
        delay(5000L)
        landingData = viewModel.getLandingData()
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = landingData.ifEmpty { "Loading" })
    }
}

class LandingScreenViewModel : ViewModel(), KoinComponent {

    fun getLandingData(): String {
        return getKoin().getScope("login_logout_session").get<LandingRepository>().getLandingData()
    }
}