package dev.vengateshm.compose_material3.api_kotlin.coroutines.flow_operators

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ContactListScreen(
    modifier: Modifier = Modifier,
    viewModel: ContactViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle(initialValue = ContactState())

    ContactList(
        state = state,
        onSortByClick = {
            viewModel.sortBy(it)
        }
    )
}

@Composable
fun ContactList(
    modifier: Modifier = Modifier,
    state: ContactState,
    onSortByClick: (SortType) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Sort By"
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FilterChip(
                selected = state.sortType == SortType.FIRST_NAME,
                onClick = { onSortByClick(SortType.FIRST_NAME) },
                label = { Text(text = "First Name") })
            FilterChip(
                selected = state.sortType == SortType.LAST_NAME,
                onClick = { onSortByClick(SortType.LAST_NAME) },
                label = { Text(text = "Last Name") })
            FilterChip(
                selected = state.sortType == SortType.PHONE_NUMBER,
                onClick = { onSortByClick(SortType.PHONE_NUMBER) },
                label = { Text(text = "Phone Number") })
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.contacts) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "${it.firstName} ${it.lastName}\n${it.phoneNumber}"
                )
                Spacer(modifier = Modifier.height(4.dp))
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ContactListPreview() {
    ContactList(
        state = ContactState(contacts = contacts),
        onSortByClick = {}
    )
}