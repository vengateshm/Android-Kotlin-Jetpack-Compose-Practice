package dev.vengateshm.android_kotlin_compose_practice.performance_tips

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Checkbox
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf


@Composable
fun ContactListScreen() {
    var selected by remember { mutableStateOf(false) }
    Column {
        Checkbox(checked = selected, onCheckedChange = {
            selected = it
        })
        //ContactList(state = ContactListState(false, listOf("Person")))
        ImmutableContactList(isLoading = false, names = persistentListOf("Person"))
    }
}

@Composable
fun ContactList(
    state: ContactListState
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Text(text = state.names.toString())
        }
    }
}

@Composable
fun ImmutableContactList(
    isLoading: Boolean,
    names: ImmutableList<String>
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Text(text = names.toString())
        }
    }
}

//@Stable
@Immutable
data class ContactListState(
    val isLoading: Boolean,
    val names: List<String>
)