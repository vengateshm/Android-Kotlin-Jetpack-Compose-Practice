package dev.vengateshm.android_kotlin_compose_practice.text_apis

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SelectableText() {
    SelectionContainer {
        Column {
            Text(text = "This text can be selected")
            // This makes text un selectable
            DisableSelection {
                Text(text = "This text cannot be selected")
            }
        }
    }
}

