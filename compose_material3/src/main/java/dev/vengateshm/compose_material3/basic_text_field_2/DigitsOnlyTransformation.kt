package dev.vengateshm.compose_material3.basic_text_field_2

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.input.InputTransformation
import androidx.compose.foundation.text2.input.TextFieldBuffer
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly

@OptIn(ExperimentalFoundationApi::class)
object DigitsOnlyTransformation : InputTransformation {
    /*override val keyboardOptions: KeyboardOptions
        get() = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )*/

    override fun transformInput(
        originalValue: TextFieldCharSequence,
        valueWithChanges: TextFieldBuffer
    ) {
        if(!valueWithChanges.asCharSequence().isDigitsOnly()){
            valueWithChanges.revertAllChanges()
        }
    }
}