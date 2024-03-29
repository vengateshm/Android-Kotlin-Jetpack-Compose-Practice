package dev.vengateshm.android_kotlin_compose_practice.performance_tips

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Checkbox
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun ContactListScreen() {
    var selected by remember { mutableStateOf(false) }
    Column {
        Checkbox(checked = selected, onCheckedChange = {
            selected = it
        })
//        ContactList(isLoading = false, names = listOf("Person"))
//        ContactList(state = ContactListState(false, listOf("Person")))
//        ImmutableContactList(isLoading = false, names = persistentListOf("Person"))
    }
}

@Composable
fun ContactListScreen(viewModel: ContactListViewModel) {
    val isSelected = viewModel.isSelected.collectAsStateWithLifecycle()
    val state = viewModel.state.collectAsStateWithLifecycle()
    Column {
        Checkbox(
            checked = isSelected.value,
            onCheckedChange = /*{
            viewModel.checkboxState(it)
        }*/viewModel::checkboxState,
        )
        ContactList(
            isLoading = { state.value.isLoading },
            names = { state.value.names },
        )
    }
}

@Composable
fun ContactList(
    isLoading: () -> Boolean,
    names: () -> List<String>,
) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading()) {
            CircularProgressIndicator()
        } else {
            Text(text = names().toString())
        }
    }
}

@Composable
fun ContactListWithState(state: ContactListState) {
    Box(
        contentAlignment = Alignment.Center,
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
    names: ImmutableList<String>,
) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Text(text = names.toString())
        }
    }
}

// @Stable
@Immutable
data class ContactListState(
    val isLoading: Boolean,
    val names: List<String>,
)

data class ContactListUiState(
    val isLoading: Boolean,
    val names: List<String>,
)

class ContactListViewModel : ViewModel() {
    private val _isSelected = MutableStateFlow(false)
    val isSelected = _isSelected.asStateFlow()

    private val _state =
        MutableStateFlow(
            ContactListUiState(
                isLoading = false,
                names = persistentListOf("Person"),
            ),
        )

    val state = _state.asStateFlow()

    fun checkboxState(value: Boolean) {
        _isSelected.value = value
    }
}
