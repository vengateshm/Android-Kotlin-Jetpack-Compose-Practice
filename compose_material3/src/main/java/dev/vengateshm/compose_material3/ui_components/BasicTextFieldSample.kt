package dev.vengateshm.compose_material3.ui_components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun BasicTextFieldSample() {
    var text by remember { mutableStateOf("") }
    val secureTextFieldState = rememberTextFieldState()

    LaunchedEffect(true) {
        delay(5000)
        text = "Hello World!"
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // BasicTextField prevents over writing text
        // when in edit state
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 60.dp)
                .border(width = 1.dp, color = Color.Black),
            value = text,
            onValueChange = {
                text = it
            })
        Spacer(modifier = Modifier.height(24.dp))
        BasicSecureTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 60.dp)
                .border(width = 1.dp, color = Color.Black),
            state = secureTextFieldState,
        )
    }
}


val StaticTextTransformation = InputTransformation {
    if (this.asCharSequence().contains("hi")) {
        this.revertAllChanges()
    }
}