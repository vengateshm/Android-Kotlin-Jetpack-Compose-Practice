package dev.vengateshm.compose_material3.apps.calendar.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import dev.vengateshm.compose_material3.apps.calendar.CalendarEventViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddEventScreen(viewModel: CalendarEventViewModel, onEventAdded: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var openDatePicker by remember { mutableStateOf(false) }
    var openTimePicker by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Add Event",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = viewModel.dateStringStart.ifEmpty { "Start Date" })
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        viewModel.isStartDateTimeSelected = true
                        openDatePicker = true
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.CalendarMonth,
                            contentDescription = null
                        )
                    }
                }
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = viewModel.timeStringStart.ifBlank { "Start Time" })
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        viewModel.isStartDateTimeSelected = true
                        openTimePicker = true
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.AccessTime,
                            contentDescription = null
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = viewModel.dateStringEnd.ifEmpty { "End Date" })
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        viewModel.isStartDateTimeSelected = false
                        openDatePicker = true
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.AccessTime,
                            contentDescription = null
                        )
                    }
                }
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = viewModel.timeStringEnd.ifEmpty { "End Time" })
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        viewModel.isStartDateTimeSelected = false
                        openTimePicker = true
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.CalendarMonth,
                            contentDescription = null
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.writeEvent(title, description)
                    onEventAdded()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add")
            }
        }

        DatePickerItem(
            open = openDatePicker,
            onDismiss = { openDatePicker = false }) { dateInMillis ->
            if (viewModel.isStartDateTimeSelected) {
                viewModel.startDateInMillis = dateInMillis
            } else {
                viewModel.endDateInMillis = dateInMillis
            }
        }
        TimePickerItem(open = openTimePicker, onDismiss = { openTimePicker = false }) { timeData ->
            if (viewModel.isStartDateTimeSelected) {
                viewModel.startTime = timeData
            } else {
                viewModel.endTime = timeData
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerItem(open: Boolean, onDismiss: () -> Unit, onDatePicked: (Long) -> Unit) {
    if (open) {
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = {
                onDismiss()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDatePicked(datePickerState.selectedDateMillis ?: 0L)
                        onDismiss()
                    }
                ) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text("Cancel")
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerItem(open: Boolean, onDismiss: () -> Unit, onTimePicked: (TimeData?) -> Unit) {
    if (open) {
        val timePickerState = rememberTimePickerState()
        Dialog(
            onDismissRequest = {
                onDismiss()
            }) {
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(24.dp)
            ) {
                TimePicker(state = timePickerState)
                TextButton(
                    onClick = {
                        onTimePicked(
                            TimeData(
                                hour = timePickerState.hour,
                                min = timePickerState.minute,
                                is24Hr = timePickerState.is24hour
                            )
                        )
                        onDismiss()
                    },
                ) {
                    Text(
                        text = "OK"
                    )
                }
            }
        }
    }
}

data class TimeData(
    val hour: Int,
    val min: Int,
    val is24Hr: Boolean
)