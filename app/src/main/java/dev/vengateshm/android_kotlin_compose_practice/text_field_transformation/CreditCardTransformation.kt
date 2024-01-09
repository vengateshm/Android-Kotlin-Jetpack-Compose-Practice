package dev.vengateshm.android_kotlin_compose_practice.text_field_transformation

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CreditCardVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val formatted = formatCardNumberWithHyphens(text.text)
        println("Formatted text : $formatted")
        val offsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val formattedLength = formatted.length
                val transformedOffset =
                    if (offset <= formattedLength) offset + formattedLength - text.length else offset
                println("Length : $formattedLength Original offset : $offset Transformed offset : $transformedOffset")
                return transformedOffset
            }

            override fun transformedToOriginal(offset: Int): Int {
                val formattedLength = formatted.length
                return if (offset <= formattedLength) offset else offset - formattedLength + text.length
            }
        }
        return TransformedText(AnnotatedString(formatted), offsetTranslator)
    }
}

@Composable
fun CreditCardTextField() {
    val cardNumber = remember { mutableStateOf("") }

    TextField(
        value = cardNumber.value,
        onValueChange = {
            if (it.length <= 16) {
                cardNumber.value = it
            }
        },
        visualTransformation = CreditCardVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        placeholder = { Text(text = "XXXX-XXXX-XXXX-XXXX") },
        maxLines = 1
    )
}

private fun formatCardNumberWithHyphens(cardNumber: String): String {
    val formatted = cardNumber.chunked(4).joinToString("-")
    // Handle potential trailing hyphen if the length is not a multiple of 4
    return formatted.dropLast(if (formatted.endsWith("-")) 1 else 0)
}
