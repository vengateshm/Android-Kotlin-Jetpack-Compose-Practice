package dev.vengateshm.compose_material3.basic_text_field_2

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text2.BasicSecureTextField
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.InputTransformation
import androidx.compose.foundation.text2.input.TextFieldBuffer
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.foundation.text2.input.rememberTextFieldState
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasicTextField2Sample() {
    // BasicTextField2 prevents over writing text
    // when in edit state
    var text by remember {
        mutableStateOf("")
    }
    var securedText by remember {
        mutableStateOf("")
    }

    LaunchedEffect(true) {
        delay(5000)
        text = "Hello World!"
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        BasicTextField2(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 60.dp)
                .border(width = 1.dp, color = Color.Black),
            value = text,
            onValueChange = {
                text = it
            },
            inputTransformation = StaticTextTransformation
        )
        Spacer(modifier = Modifier.height(24.dp))
        BasicSecureTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 60.dp)
                .border(width = 1.dp, color = Color.Black),
            value = securedText,
            onValueChange = {
                securedText = it
            })
        val textState = rememberTextFieldState()
        BasicTextField2(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 60.dp)
                .border(width = 1.dp, color = Color.Black),
            state = textState
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
object StaticTextTransformation : InputTransformation {
    override fun transformInput(
        originalValue: TextFieldCharSequence,
        valueWithChanges: TextFieldBuffer
    ) {
        if (valueWithChanges.asCharSequence().contains("hi")) {
            valueWithChanges.revertAllChanges()
        }
    }
}