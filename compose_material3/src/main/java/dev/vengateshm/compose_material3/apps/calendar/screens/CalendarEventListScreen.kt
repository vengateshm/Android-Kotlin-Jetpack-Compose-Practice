package dev.vengateshm.compose_material3.apps.calendar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import dev.vengateshm.compose_material3.apps.calendar.CalendarEvent
import dev.vengateshm.compose_material3.apps.calendar.CalendarEventUiState
import dev.vengateshm.compose_material3.apps.calendar.CalendarEventViewModel
import dev.vengateshm.compose_material3.apps.calendar.DateUtils.formatEventTime
import dev.vengateshm.compose_material3.apps.calendar.DateUtils.formatStartDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarEventListScreen(viewModel: CalendarEventViewModel, onAddEventClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Events") },
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    onAddEventClick()
                }) {
                Text("Add Event")
            }
        }
    ) { paddingValues ->
        val uiState = viewModel.uiState.collectAsState()

        when (val state = uiState.value) {
            CalendarEventUiState.Loading -> {
                CenteredCircleProgressBar(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                )
            }

            is CalendarEventUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues = paddingValues)
                ) {
                    items(state.events) { event ->
                        CalendarEventItem(event)
                    }
                }
            }

            is CalendarEventUiState.Error -> {
                Text(text = "Error: ${state.message}")
            }
        }
    }
    // Observe Lifecycle events
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.getEventsByDate(System.currentTimeMillis())
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        // Dispose the observer on composable recomposition
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Composable
private fun CalendarEventItem(event: CalendarEvent) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = CardDefaults.elevatedShape,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
                        )
                    )
                )
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = formatStartDate(event),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = event.title.orEmpty(),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = event.description.orEmpty(),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = formatEventTime(event),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}