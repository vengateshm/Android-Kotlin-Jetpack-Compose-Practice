package dev.vengateshm.compose_material3.api_kotlin.coroutines

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.coroutineContext
import kotlin.time.Duration.Companion.seconds

suspend fun pollApi(client: HttpClient) {
    while (true) {
        try {
            println("Polling...")
            val response = client.get("https://jsonplaceholder.typicode.com/todos/1")
            println("Received response.")
            delay(10.seconds)
        } catch (e: Exception) {
            //if (e is CancellationException) throw e
            coroutineContext.ensureActive()
            println("Exception occured! ${e.localizedMessage}")
        }
    }
}

private val httpClient by lazy {
    HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
        }
    }
}

class PollViewModel : ViewModel() {
    var isPolling by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            isPolling = true
            pollApi(httpClient)
            isPolling = false
        }
    }
}

@Composable
fun CoroutinesPollingApiScenario(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "land") {
        composable("land") {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        navController.navigate("poll")
                    },
                ) {
                    Text(text = "Go to Polling Screen")
                }
            }
        }
        composable("poll") {
            viewModel<PollViewModel>()
        }
    }
}