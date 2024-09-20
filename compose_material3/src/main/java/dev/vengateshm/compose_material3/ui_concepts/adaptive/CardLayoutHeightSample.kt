package dev.vengateshm.compose_material3.ui_concepts.adaptive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.api_compose.preview_provider.ReferenceDevices
import dev.vengateshm.compose_material3.ui_concepts.adaptive.CardLayoutHeightConfig.cardHeightInDp
import dev.vengateshm.compose_material3.ui_concepts.multiple_screen_size_support.WindowType
import dev.vengateshm.compose_material3.ui_concepts.multiple_screen_size_support.getWindowSize

@Composable
fun CardLayoutHeightSample(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeightInDp)
            .background(color = Color.LightGray),
    ) {

    }
}

object CardLayoutHeightConfig {
    val cardHeightInDp: Dp
        @Composable
        get() {
            return when (getWindowSize().width) {
                WindowType.Compact -> 100.dp
                WindowType.Medium -> 150.dp
                WindowType.Expanded -> 200.dp
            }
        }
}

@ReferenceDevices
@Composable
private fun CardLayoutHeightSamplePreview() {
    MaterialTheme {
        Surface {
            CardLayoutHeightSample()
        }
    }
}