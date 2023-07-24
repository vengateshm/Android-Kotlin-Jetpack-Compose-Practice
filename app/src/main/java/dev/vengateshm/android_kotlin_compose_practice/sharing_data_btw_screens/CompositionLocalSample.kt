package dev.vengateshm.android_kotlin_compose_practice.sharing_data_btw_screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

/**
 * Can be used to share data between composables.
 * It might affect previews.
 * */

val LocalSnackBarHostState = compositionLocalOf {
    SnackbarHostState()
}

// App Root
@Composable
fun CompositionLocalSample() {
    val snackbarHostState = LocalSnackBarHostState.current
    CompositionLocalProvider(LocalSnackBarHostState provides snackbarHostState) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = LocalSnackBarHostState.current)
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                MyScreen()
            }

        }
    }
}

@Composable
fun MyScreen() {
    val snackBarHostState = LocalSnackBarHostState.current
    val scope = rememberCoroutineScope()
    Button(onClick = {
        scope.launch {
            snackBarHostState.showSnackbar("Hello SnackBar!")
        }
    }) {
        Text(text = "Show SnackBar")
    }
}
