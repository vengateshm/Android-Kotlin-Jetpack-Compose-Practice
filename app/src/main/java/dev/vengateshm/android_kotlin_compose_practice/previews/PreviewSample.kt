package dev.vengateshm.android_kotlin_compose_practice.previews

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    title: String,
    isOutline: Boolean,
    onClick: () -> Unit
) {
    if (isOutline) {
        OutlinedButton(
            modifier = modifier,
            onClick = onClick,
            shape = RectangleShape,
            contentPadding = PaddingValues(16.dp),
            border = BorderStroke(
                width = if (LocalInspectionMode.current) 3.dp else 1.dp,
                color = MaterialTheme.colors.primary
            )
        ) {
            Text(text = title)
        }
    } else {
        Button(
            modifier = modifier,
            onClick = onClick,
            shape = RectangleShape,
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(text = title)
        }
    }
}

/*@Preview(
    *//*widthDp = 300,
    heightDp = 300*//*
    showSystemUi = true,
    locale = "de",
    fontScale = 1f,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.NEXUS_10

)
@Composable
fun CustomButtonPreview() {
    CustomButton(
        title = "Hello World!",
        isOutline = true
    ) {

    }
}*/

@Preview
//@PreviewScreenSizes
//@PreviewFontScale
//@PreviewLightDark
//@PreviewDynamicColors
@Composable
fun CustomButtonPreviewOutline(
    @PreviewParameter(OutlinedPreviewParameterProvider::class) isOutline: Boolean
) {
    MaterialTheme {
        Surface {
            CustomButton(
                title = "Hello World!",
                isOutline = isOutline
            ) {

            }
        }
    }
}

// Make sure to pass state to the composable
// viewmodel, usecase, repository can be avoided
class OutlinedPreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(false, true)
}