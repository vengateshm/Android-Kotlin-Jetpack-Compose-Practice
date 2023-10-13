package dev.vengateshm.android_kotlin_compose_practice.mvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

// Model
data class AndroidStudioVersion(val name: String)

// Intent
sealed class HomeScreenIntent {
    data object FetchVersions : HomeScreenIntent()
}

// State
sealed class HomeScreenState {
    data object Idle : HomeScreenState()
    data object Loading : HomeScreenState()
    data class AndroidStudioVersions(val versions: List<AndroidStudioVersion>) : HomeScreenState()
    data class Error(val error: String) : HomeScreenState()
}

// Repository
class AndroidStudioVersionsRepository {
    suspend fun getVersions(): List<AndroidStudioVersion> {
        delay(500L) // Simulating a network delay
        return listOf(
            AndroidStudioVersion("Arctic Fox"),
            AndroidStudioVersion("Bumblebee"),
            AndroidStudioVersion("Chipmunk"),
            AndroidStudioVersion("Dolphin"),
            AndroidStudioVersion("Electric Eel"),
            AndroidStudioVersion("Flamingo"),
            AndroidStudioVersion("Giraffe"),
            AndroidStudioVersion("Hedge Hog"),
            AndroidStudioVersion("Iguana")
        )
    }
}

// ViewModel
class HomeScreenViewModel(
    private val repository: AndroidStudioVersionsRepository =
        AndroidStudioVersionsRepository(),
) : ViewModel() {

    private val userIntent = Channel<HomeScreenIntent>(capacity = Channel.UNLIMITED)
    val state = mutableStateOf<HomeScreenState>(HomeScreenState.Idle)

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect { intent ->
                when (intent) {
                    is HomeScreenIntent.FetchVersions -> fetchVersions()
                }
            }
        }
    }

    // Expose a function to send intents to the ViewModel
    fun sendIntent(intent: HomeScreenIntent) {
        viewModelScope.launch {
            userIntent.send(intent)
        }
    }

    private fun fetchVersions() {
        viewModelScope.launch {
            state.value = HomeScreenState.Loading
            state.value = try {
                HomeScreenState.AndroidStudioVersions(repository.getVersions())
            } catch (e: Exception) {
                HomeScreenState.Error(e.localizedMessage ?: "Error fetching versions")
            }
        }
    }
}

// ModelViewIntentSampleActivity
class ModelViewIntentSampleActivity : ComponentActivity() {

    private val viewModel: HomeScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colors.background) {
                    HomeScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {
    when (val state = viewModel.state.value) {
        is HomeScreenState.Idle -> IdleUI(onButtonClick = {
            viewModel.sendIntent(HomeScreenIntent.FetchVersions)
        })

        is HomeScreenState.Loading -> LoadingUI()
        is HomeScreenState.AndroidStudioVersions -> VersionsUI(state.versions)
        is HomeScreenState.Error -> IdleUI(onButtonClick = {
            viewModel.sendIntent(HomeScreenIntent.FetchVersions)
        }, state.error)
    }
}

// Idle UI
@Composable
fun IdleUI(onButtonClick: () -> Unit, error: String? = null) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (error != null) {
                Text(text = error)
            }
            Button(onClick = { onButtonClick() }) {
                Text(text = "Fetch Android Studio Versions")
            }
        }
    }
}

// Loading UI
@Composable
fun LoadingUI() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

// Versions list UI
@Composable
fun VersionsUI(versions: List<AndroidStudioVersion>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(versions) { version ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = CutCornerShape(topStart = 20.dp, bottomEnd = 20.dp),
                backgroundColor = Color(0xFFD0E6F1),
                elevation = 4.dp
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = version.name, fontSize = 18.sp,
                    color = Color(0xFF455A64)
                )
            }
        }
    }
}
