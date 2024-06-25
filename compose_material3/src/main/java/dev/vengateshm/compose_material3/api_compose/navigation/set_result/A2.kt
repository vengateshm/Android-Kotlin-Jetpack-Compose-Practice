package dev.vengateshm.compose_material3.api_compose.navigation.set_result

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

@Composable
fun A2(
    onResult: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            onResult("Hello from A2")
        }) {
            Text(text = "Set Result")
        }
    }
}

fun NavGraphBuilder.addA2(onResult: (String) -> Unit) {
    composable(route = "a2") {
        A2(onResult = onResult)
    }
}