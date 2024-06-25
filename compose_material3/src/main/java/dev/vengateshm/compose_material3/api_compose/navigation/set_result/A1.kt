package dev.vengateshm.compose_material3.api_compose.navigation.set_result

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.animation.AnimatedVisibility
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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.vengateshm.compose_material3.api_compose.navigation.getResultFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun A1(
    onGoToA2: () -> Unit,
    onGoToA3: () -> Unit,
    result: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            onGoToA2()
        }) {
            Text(text = "Go to A2")
        }
        Button(onClick = {
            onGoToA3()
        }) {
            Text(text = "Go to A3")
        }
        AnimatedVisibility(visible = result.isNotEmpty()) {
            Text(text = result)
        }
    }
}

fun NavGraphBuilder.addA1(onGoToA2: () -> Unit) {
    composable(route = "a1") { navBackStackEntry ->
        var result by remember { mutableStateOf("") }
        LaunchedEffect(Unit) {
            navBackStackEntry.getResultFlow<String?>(null)
                .collectLatest {
                    result = it ?: ""
                }
        }

        val launcher = rememberLauncherForActivityResult(
            contract = A3ActivityResultContract(),
            onResult = {
                result = it ?: ""
            }
        )

        A1(
            onGoToA2 = onGoToA2,
            onGoToA3 = {
                launcher.launch(null)
            },
            result = result
        )
    }
}