package dev.vengateshm.android_kotlin_compose_practice.sharing_data_btw_screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Composable
fun GlobalSingletonSample() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = "screen1"
    ) {
        composable("screen1") {
            val viewModel = hiltViewModel<Screen1ViewModel>()
            val count by viewModel.count.collectAsStateWithLifecycle()
            Screen1(count = count, onNavigateToScreen2 = {
                viewModel.inc()
                navController.navigate("screen2")
            })
        }
        composable("screen2") {
            val viewModel = hiltViewModel<Screen2ViewModel>()
            val count by viewModel.count.collectAsStateWithLifecycle()
            Screen2(count = count, viewModel = viewModel)
        }
    }
}

@Composable
fun Screen1(
    count: Int,
    onNavigateToScreen2: () -> Unit,
) {
    Column {
        Text(text = "Screen1 $count")
        Button(onClick = {
            onNavigateToScreen2()
        }) {
            Text(text = "Click!")
        }
    }
}

@HiltViewModel
class Screen1ViewModel @Inject constructor(private val globalState: GlobalState) : ViewModel() {
    val count = globalState.globalCounter

    fun inc() {
        globalState.incrementCounter()
    }
}

@Composable
fun Screen2(count: Int, viewModel: Screen2ViewModel) {
    Column {
        Text(text = "Screen2 $count")
        Button(onClick = { viewModel.inc() }) {
            Text(text = "Click!")
        }
    }
}

@HiltViewModel
class Screen2ViewModel @Inject constructor(private val globalState: GlobalState) : ViewModel() {
    val count = globalState.globalCounter

    fun inc() {
        globalState.incrementCounter()
    }
}

@Singleton
class GlobalState @Inject constructor() {
    private val _globalCounter = MutableStateFlow(0)
    val globalCounter = _globalCounter.asStateFlow()

    fun incrementCounter() {
        _globalCounter.value++
    }
}