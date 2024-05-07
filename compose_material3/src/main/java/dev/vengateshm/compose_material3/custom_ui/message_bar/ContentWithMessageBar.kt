package dev.vengateshm.compose_material3.custom_ui.message_bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.custom_ui.message_bar.TestTags.COPY_BUTTON
import dev.vengateshm.compose_material3.custom_ui.message_bar.TestTags.MESSAGE_BAR
import dev.vengateshm.compose_material3.custom_ui.message_bar.TestTags.MESSAGE_BAR_TEXT
import java.util.Timer
import kotlin.concurrent.schedule

@Composable
fun ContentWithMessageBar(
    modifier: Modifier = Modifier,
    messageBarState: MessageBarState,
    position: MessageBarPosition = MessageBarPosition.TOP,
    successIcon: ImageVector = Icons.Default.Check,
    errorIcon: ImageVector = Icons.Default.Warning,
    successMaxLines: Int = 1,
    errorMaxLines: Int = 1,
    contentBackgroundColor: Color = MaterialTheme.colorScheme.surface,
    successContainerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    successContentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    errorContainerColor: Color = MaterialTheme.colorScheme.errorContainer,
    errorContentColor: Color = MaterialTheme.colorScheme.onErrorContainer,
    verticalPadding: Dp = 12.dp,
    horizontalPadding: Dp = 12.dp,
    enterAnimation: EnterTransition = expandVertically(
        animationSpec = tween(durationMillis = 300),
        expandFrom = if (position == MessageBarPosition.TOP) Alignment.Top else Alignment.Bottom
    ),
    exitAnimation: ExitTransition = shrinkVertically(
        animationSpec = tween(durationMillis = 300),
        shrinkTowards = if (position == MessageBarPosition.TOP) Alignment.Top else Alignment.Bottom
    ),
    visibilityDuration: Long = 3000L,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = contentBackgroundColor)
    ) {
        content()
        MessageBarComponent(
            messageBarState = messageBarState,
            position = position,
            successIcon = successIcon,
            errorIcon = errorIcon,
            successMaxLines = successMaxLines,
            errorMaxLines = errorMaxLines,
            successContainerColor = successContainerColor,
            successContentColor = successContentColor,
            errorContainerColor = errorContainerColor,
            errorContentColor = errorContentColor,
            verticalPadding = verticalPadding,
            horizontalPadding = horizontalPadding,
            enterAnimation = enterAnimation,
            exitAnimation = exitAnimation,
            visibilityDuration = visibilityDuration
        )
    }
}

@Composable
internal fun BoxScope.MessageBarComponent(
    messageBarState: MessageBarState,
    position: MessageBarPosition,
    successIcon: ImageVector,
    errorIcon: ImageVector,
    successMaxLines: Int,
    errorMaxLines: Int,
    successContainerColor: Color,
    successContentColor: Color,
    errorContainerColor: Color,
    errorContentColor: Color,
    verticalPadding: Dp,
    horizontalPadding: Dp,
    enterAnimation: EnterTransition,
    exitAnimation: ExitTransition,
    visibilityDuration: Long,
) {
    var showMessageBar by remember { mutableStateOf(false) }
    val success by rememberUpdatedState(newValue = messageBarState.success)
    val error by rememberUpdatedState(newValue = messageBarState.error?.message)

    /*LaunchedEffect(key1 = messageBarState.updated) {
        showMessageBar = true
        delay(visibilityDuration)
        showMessageBar = false
    }*/

    DisposableEffect(key1 = messageBarState.updated) {
        showMessageBar = true
        val timer = Timer("Animation Timer", true)
        timer.schedule(visibilityDuration) {
            showMessageBar = false
        }
        onDispose {
            timer.cancel()
            timer.purge()
        }
    }

    AnimatedVisibility(
        visible = (success != null || error != null) && showMessageBar,
        enter = enterAnimation,
        exit = exitAnimation
    ) {
        MessageBar(
            success = success,
            error = error,
            position = position,
            successIcon = successIcon,
            errorIcon = errorIcon,
            successMaxLines = successMaxLines,
            errorMaxLines = errorMaxLines,
            successContainerColor = successContainerColor,
            successContentColor = successContentColor,
            errorContainerColor = errorContainerColor,
            errorContentColor = errorContentColor,
            verticalPadding = verticalPadding,
            horizontalPadding = horizontalPadding
        )
    }
}

@Composable
internal fun BoxScope.MessageBar(
    success: String?,
    error: String?,
    position: MessageBarPosition,
    successIcon: ImageVector,
    errorIcon: ImageVector,
    successMaxLines: Int,
    errorMaxLines: Int,
    successContainerColor: Color,
    successContentColor: Color,
    errorContainerColor: Color,
    errorContentColor: Color,
    verticalPadding: Dp,
    horizontalPadding: Dp,
) {
    val clipboardManager = LocalClipboardManager.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (error != null) errorContainerColor else successContainerColor)
            .padding(vertical = verticalPadding)
            .padding(horizontal = horizontalPadding)
            .animateContentSize()
            .align(if (position == MessageBarPosition.TOP) Alignment.TopStart else Alignment.BottomStart)
            .testTag(MESSAGE_BAR),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = if (error != null) errorIcon else successIcon,
            contentDescription = "Message Bar Icon",
            tint = if (error != null) errorContentColor else successContentColor
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            modifier = Modifier
                .weight(weight = 1f)
                .testTag(tag = MESSAGE_BAR_TEXT),
            text = success ?: (error ?: "Unknown"),
            color = if (error != null) errorContentColor else successContentColor,
            fontSize = MaterialTheme.typography.labelLarge.fontSize,
            overflow = TextOverflow.Ellipsis,
            maxLines = if (error != null) errorMaxLines else successMaxLines
        )
        Spacer(modifier = Modifier.width(12.dp))
        if (error != null) {
            TextButton(
                modifier = Modifier.testTag(tag = COPY_BUTTON), onClick = {
                    clipboardManager.setText(AnnotatedString(text = "$error"))
                }) {
                Text(
                    text = "Copy",
                    color = errorContentColor,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
internal fun MessageBarSuccessPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        MessageBar(
            success = "Successfully Updated.",
            error = null,
            position = MessageBarPosition.TOP,
            successIcon = Icons.Default.Check,
            errorIcon = Icons.Default.Warning,
            successMaxLines = 1,
            errorMaxLines = 1,
            successContainerColor = MaterialTheme.colorScheme.primaryContainer,
            successContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            errorContainerColor = MaterialTheme.colorScheme.errorContainer,
            errorContentColor = MaterialTheme.colorScheme.onErrorContainer,
            verticalPadding = 12.dp,
            horizontalPadding = 12.dp
        )
    }
}

@Composable
@Preview(showBackground = true)
internal fun MessageBarErrorPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        MessageBar(
            success = null,
            error = "Internet Unavailable.",
            position = MessageBarPosition.BOTTOM,
            successIcon = Icons.Default.Check,
            errorIcon = Icons.Default.Warning,
            successMaxLines = 1,
            errorMaxLines = 1,
            successContainerColor = MaterialTheme.colorScheme.primaryContainer,
            successContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            errorContainerColor = MaterialTheme.colorScheme.errorContainer,
            errorContentColor = MaterialTheme.colorScheme.onErrorContainer,
            verticalPadding = 12.dp,
            horizontalPadding = 12.dp
        )
    }
}