package dev.vengateshm.compose_material3.custom_colors

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomColorSample(modifier: Modifier = Modifier) {
    val customColors = if (isSystemInDarkTheme()) darkThemeColors else lightThemeColors

    CompositionLocalProvider(LocalCustomColors provides customColors) {
        MaterialTheme {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    val isError = true
                    val interactionSource = remember { MutableInteractionSource() }
                    val isFocused = interactionSource.collectIsFocusedAsState()
                    val borderColor by animateColorAsState(
                        targetValue = if (isError)
                            LocalCustomColors.current.borderError
                        else if (isFocused.value)
                            LocalCustomColors.current.borderFocused
                        else
                            Color.Transparent,
                        animationSpec = tween(durationMillis = 300),
                    )
                    OutlinedTextField(
                        shape = RoundedCornerShape(12.dp),
                        value = "",
                        onValueChange = {},
                        placeholder = { Text("Email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .border(
                                width = 1.dp,
                                color = borderColor,
                                shape = RoundedCornerShape(12.dp),
                            )
                            .wrapContentHeight(),
                    )

                    Button(
                        onClick = { },
                        enabled = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LocalCustomColors.current.buttonPrimary,
                            contentColor = LocalCustomColors.current.textInverse,
                            disabledContainerColor = LocalCustomColors.current.buttonDisabled,
                            disabledContentColor = LocalCustomColors.current.textInverse.copy(alpha = 0.38f),
                        ),
                    ) {
                        Text("Send")
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun CustomColorSamplePreview() {
    CustomColorSample()
}