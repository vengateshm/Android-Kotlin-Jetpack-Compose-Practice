package dev.vengateshm.compose_material3.custom_ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldWithLength(
    modifier: Modifier = Modifier,
    minLength: Int = 10,
) {
    var text by remember { mutableStateOf("") }
    var isError = remember {
        derivedStateOf {
            text.length > minLength
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                text = it
            },
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.align(Alignment.End),
            text = "${text.length} / $minLength",
            color = if (isError.value) Color.Red else Color.Black,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TextFieldWithLengthPreview() {
    TextFieldWithLength()
}