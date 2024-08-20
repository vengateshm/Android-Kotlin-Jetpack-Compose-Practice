package dev.vengateshm.compose_material3.api_compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun ComposableApiDesignSample(
    requiredParam: String, // Required
    modifier: Modifier = Modifier, // Optional
    content: @Composable () -> Unit, // Trailing content lambda
) {

}

// Return UI
@Composable
fun Header(modifier: Modifier = Modifier) {

}

// Return state
class HeaderData

@Composable
fun rememberHeaderState(): HeaderData {
    return remember {
        HeaderData()
    }
}