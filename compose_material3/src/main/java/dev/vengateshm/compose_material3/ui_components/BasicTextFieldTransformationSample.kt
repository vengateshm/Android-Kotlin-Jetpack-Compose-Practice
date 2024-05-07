package dev.vengateshm.compose_material3.ui_components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly

@Composable
fun BasicTextFieldTransformationSample() {
    val textState1 = rememberTextFieldState()
    val textState2 = rememberTextFieldState()
    val textState3 = rememberTextFieldState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Digits only transformation")
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 60.dp)
                    .border(width = 1.dp, color = Color.Black),
                state = textState1,
                inputTransformation = DigitsOnlyTransformation
            )
            Text(text = "Max length transformation")
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 60.dp)
                    .border(width = 1.dp, color = Color.Black),
                state = textState2,
                inputTransformation = InputTransformation.Companion.maxLength(6)
            )
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 60.dp)
                    .border(width = 1.dp, color = Color.Black),
                state = textState3,
                inputTransformation = StaticTextTransformation
            )
        }
    }
}

object DigitsOnlyTransformation : InputTransformation {
    override val keyboardOptions: KeyboardOptions
        get() = KeyboardOptions(keyboardType = KeyboardType.Number)

    override fun TextFieldBuffer.transformInput() {
        if (!this.asCharSequence().isDigitsOnly()) {
            this.revertAllChanges()
        }
    }
}