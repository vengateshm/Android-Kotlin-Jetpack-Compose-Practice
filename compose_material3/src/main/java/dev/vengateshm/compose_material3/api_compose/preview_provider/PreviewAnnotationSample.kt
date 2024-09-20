package dev.vengateshm.compose_material3.api_compose.preview_provider

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "phone", device = Devices.PHONE, showBackground = true, showSystemUi = true)
@Preview(name = "tablet", device = Devices.TABLET, showBackground = true, showSystemUi = true)
@Preview(name = "foldable", device = Devices.FOLDABLE, showBackground = true, showSystemUi = true)
@Preview(name = "custom", device = "spec:width=1280dp,height=800dp,dpi=480", showBackground = true, showSystemUi = true)
@Preview(name = "desktop", device = "id:desktop_medium", showBackground = true, showSystemUi = true)
annotation class ReferenceDevices

@Composable
fun MyComposable() {
    var text by remember {
        mutableStateOf("")
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
            },
        )
        Button(onClick = { }) {
            Text(text = "Send")
        }
    }
}

@ReferenceDevices
@Composable
private fun MyComposablePreview() {
    MaterialTheme {
        Surface {
            MyComposable()
        }
    }
}