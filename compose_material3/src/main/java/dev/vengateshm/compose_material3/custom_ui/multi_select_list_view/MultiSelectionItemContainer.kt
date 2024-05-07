package dev.vengateshm.compose_material3.custom_ui.multi_select_list_view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MultiSelectionItemContainer(
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    isSelected: Boolean,
    enableMultiSelectionMode: (Boolean) -> Unit,
    checkboxBackgroundColor: Color = MaterialTheme.colorScheme.background,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp)
            .combinedClickable(
                onClick = {
                    onClick()
                },
                onLongClick = {
                    enableMultiSelectionMode(true)
                    onClick()
                }
            ),
        contentAlignment = Alignment.CenterEnd
    ) {
        content()
        AnimatedVisibility(
            modifier = Modifier.background(color = checkboxBackgroundColor),
            visible = isEnabled,
            enter = slideInHorizontally(initialOffsetX = { it / 2 }) + fadeIn(),
            exit = slideOutHorizontally(targetOffsetX = { it / 2 }) + fadeOut()
        ) {
            RadioButton(selected = isSelected, onClick = onClick)
        }
    }
}