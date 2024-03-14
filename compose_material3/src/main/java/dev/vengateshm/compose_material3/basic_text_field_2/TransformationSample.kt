package dev.vengateshm.compose_material3.basic_text_field_2

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.InputTransformation
import androidx.compose.foundation.text2.input.allCaps
import androidx.compose.foundation.text2.input.maxLengthInChars
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransformationSample() {
    val (text1, setText1) = remember {
        mutableStateOf("")
    }
    val (text2, setText2) = remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Digits only transformation")
            BasicTextField2(
                value = text1,
                onValueChange = {
                    setText1(it)
                },
                inputTransformation = DigitsOnlyTransformation
            )
            Text(text = "Max length transformation")
            BasicTextField2(
                value = text2,
                onValueChange = {
                    setText2(it)
                },
                inputTransformation = InputTransformation.maxLengthInChars(6)
            )
        }
    }
}