package dev.vengateshm.compose_material3.custom_ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.delete
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchScreenViewModel = viewModel<SearchScreenViewModel>(),
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Search") }) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp),
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black,
                )
                BasicTextField(state = viewModel.searchTextFieldState)
                AnimatedVisibility(visible = viewModel.searchTextFieldState.text.isNotBlank()) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete Icon",
                        tint = Color.Black,
                        modifier = Modifier.clickable {
                            viewModel.searchTextFieldState.edit {
                                delete(0, length)
                            }
                        },
                    )
                }
            }
            val searchText by viewModel.searchTextState.collectAsStateWithLifecycle()
            Text(
                text = searchText,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}

class SearchScreenViewModel : ViewModel() {
    val searchTextFieldState = TextFieldState()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val searchTextState = snapshotFlow { searchTextFieldState.text }
        .debounce(500)
        .mapLatest { if (it.isBlank()) "Search..." else it.toString() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = "Search...",
        )
}