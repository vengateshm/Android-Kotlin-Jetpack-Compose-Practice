package dev.vengateshm.compose_material3.api_compose

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun ComposableApiDesignSample(
    requiredParam: String, // Required
    modifier: Modifier = Modifier, // Modifier
    icon: @Composable (() -> Unit)? = null, // Default Nullable
    text: @Composable () -> Unit = {}, // Default Empty
    enabled: Boolean = true, // Optional
    color: Color = Color.LightGray, // Short inline styles
    shape: Shape = ComponentDefaults.componentShape(), // ComponentDefaults for long list of styles
    content: @Composable () -> Unit, // Trailing content lambda
) {

}

object ComponentDefaults {
    @Composable
    fun componentShape() = RoundedCornerShape(16.dp)
}

// Return UI
@Composable
fun Header(modifier: Modifier = Modifier) {

}

// Return state with remember
class HeaderData

@Composable
fun rememberHeaderState(): HeaderData {
    return remember {
        HeaderData()
    }
}

// Prefixing
@Composable
fun Chip(modifier: Modifier = Modifier) {

}

@Composable
fun BasicChip(modifier: Modifier = Modifier) {

}

@Composable
fun FilterChip(modifier: Modifier = Modifier) {

}

// Composition Locals
val LocalAppTheme = compositionLocalOf { }