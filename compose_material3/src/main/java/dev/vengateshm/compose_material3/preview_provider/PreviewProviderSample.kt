package dev.vengateshm.compose_material3.preview_provider

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
fun CustomText(text: String, int: Int) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = text)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = int.toString())
    }
}

class CustomTextPreviewProvider : PreviewParameterProvider<Pair<String, Int>> {
    override val values: Sequence<Pair<String, Int>>
        get() = sampleData.asSequence()
}

val sampleData = listOf(
    "hello" to 1,
    "world" to 2,
)

@Preview(showBackground = true)
@Composable
private fun CustomTextPreview(@PreviewParameter(CustomTextPreviewProvider::class) data: Pair<String, Int>) {
    CustomText(text = data.first, int = data.second)
}