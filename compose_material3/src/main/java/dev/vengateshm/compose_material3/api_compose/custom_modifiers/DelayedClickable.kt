package dev.vengateshm.compose_material3.api_compose.custom_modifiers

import androidx.compose.foundation.clickable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

fun Modifier.delayedClickable(
    waitFor: Long = 300,
    onClick: () -> Unit
): Modifier = composed(inspectorInfo = {
    name = "onClick"
    value = onClick
}) {

    var isClicked by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = isClicked) {
        if (!isClicked) {
            println("Prevented")
            return@LaunchedEffect
        }
        delay(waitFor.milliseconds)
        println("Enabled again")
        isClicked = false
    }

    this.clickable {
        if (!isClicked) {
            isClicked = true
            onClick()
        }
    }
}