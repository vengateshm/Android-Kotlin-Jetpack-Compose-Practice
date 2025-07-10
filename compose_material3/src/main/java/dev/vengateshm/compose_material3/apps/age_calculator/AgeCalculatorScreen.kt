package dev.vengateshm.compose_material3.apps.age_calculator

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AgeCalculatorScreen(
  ageCalculatorViewModel: AgeCalculatorViewModel = viewModel(),
) {
  val title by ageCalculatorViewModel.title
  val fromDate by ageCalculatorViewModel.fromDate
  val toDate by ageCalculatorViewModel.toDate
  val calculatedAge by ageCalculatorViewModel.calculatedAge
  val ageStatistics by ageCalculatorViewModel.ageStatistics

  val dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")

  var showFromDatePickerDialog by remember { mutableStateOf(false) }
  var showToDatePickerDialog by remember { mutableStateOf(false) }

  val fromDatePickerState = rememberDatePickerState(
    initialSelectedDateMillis = fromDate?.atStartOfDay(ZoneId.systemDefault())?.toInstant()
      ?.toEpochMilli()
      ?: Instant.now().toEpochMilli(),
  )
  val toDatePickerState = rememberDatePickerState(
    initialSelectedDateMillis = toDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
      .toEpochMilli(),
  )

  if (showFromDatePickerDialog) {
    DatePickerDialog(
      onDismissRequest = { showFromDatePickerDialog = false },
      confirmButton = {
        TextButton(
          onClick = {
            showFromDatePickerDialog = false
            fromDatePickerState.selectedDateMillis?.let { millis ->
              val selectedLocalDate =
                Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
              ageCalculatorViewModel.onFromDateSelected(selectedLocalDate)
            }
          },
        ) {
          Text("OK")
        }
      },
      dismissButton = {
        TextButton(onClick = { showFromDatePickerDialog = false }) {
          Text("Cancel")
        }
      },
    ) {
      DatePicker(state = fromDatePickerState)
    }
  }

  if (showToDatePickerDialog) {
    DatePickerDialog(
      onDismissRequest = { showToDatePickerDialog = false },
      confirmButton = {
        TextButton(
          onClick = {
            showToDatePickerDialog = false
            toDatePickerState.selectedDateMillis?.let { millis ->
              val selectedLocalDate =
                Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
              ageCalculatorViewModel.onToDateSelected(selectedLocalDate)
            }
          },
        ) {
          Text("OK")
        }
      },
      dismissButton = {
        TextButton(onClick = { showToDatePickerDialog = false }) {
          Text("Cancel")
        }
      },
    ) {
      DatePicker(state = toDatePickerState)
    }
  }

  Scaffold(
    containerColor = Color(0xFFF0F0F0),
  ) { paddingValues ->
    Column(
      modifier = Modifier
        .padding(paddingValues)
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Spacer(modifier = Modifier.height(32.dp))

      OutlinedTextField(
        value = title,
        onValueChange = { ageCalculatorViewModel.onTitleChange(it) },
        label = { Text("Title") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
      )

      Spacer(modifier = Modifier.height(24.dp))

      DateRow(
        label = "From",
        selectedDate = fromDate,
        dateFormatter = dateFormatter,
        onDateClick = { showFromDatePickerDialog = true }
      )

      Spacer(modifier = Modifier.height(16.dp))

      DateRow(
        label = "To",
        selectedDate = toDate,
        dateFormatter = dateFormatter,
        onDateClick = { showToDatePickerDialog = true }
      )

      Spacer(modifier = Modifier.height(32.dp))

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
      ) {
        AgeUnitText(value = calculatedAge.years.toString(), unit = "Years")
        AgeUnitText(
          value = calculatedAge.months.toString(),
          unit = "Months",
          modifier = Modifier.padding(horizontal = 16.dp),
        )
        AgeUnitText(value = calculatedAge.days.toString(), unit = "Days")
      }

      Spacer(modifier = Modifier.height(32.dp))

      // Age Statistics Section
      Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            "Age Statistics",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
          )
          Spacer(modifier = Modifier.height(16.dp))
          StatisticRow("Total Years:", ageStatistics.totalYears.toString())
          StatisticRow("Total Months:", ageStatistics.totalMonths.toString())
          StatisticRow("Total Weeks:", ageStatistics.totalWeeks.toString())
          StatisticRow("Total Days:", ageStatistics.totalDays.toString())
          StatisticRow("Total Hours:", ageStatistics.totalHours.toString())
          StatisticRow("Total Minutes:", ageStatistics.totalMinutes.toString())
          StatisticRow("Total Seconds:", ageStatistics.totalSeconds.toString())
        }
      }
      Spacer(modifier = Modifier.height(16.dp)) // For bottom padding
    }
  }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateRow(
  label: String,
  selectedDate: LocalDate?,
  dateFormatter: DateTimeFormatter,
  onDateClick: () -> Unit,
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable(onClick = onDateClick)
      .padding(vertical = 8.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    Text(label, fontSize = 16.sp, color = Color.Gray)
    Row(verticalAlignment = Alignment.CenterVertically) {
      Text(
        text = selectedDate?.format(dateFormatter) ?: "Select Date",
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
      )
      Spacer(modifier = Modifier.width(8.dp))
      Icon(
        imageVector = Icons.Default.CalendarMonth,
        contentDescription = "Select Date",
        tint = MaterialTheme.colorScheme.primary,
      )
    }
  }
}

@Composable
fun AgeUnitText(value: String, unit: String, modifier: Modifier = Modifier) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier,
  ) {
    Text(
      value,
      fontSize = 28.sp,
      fontWeight = FontWeight.Bold,
      color = MaterialTheme.colorScheme.primary,
    )
    Text(unit, fontSize = 14.sp, color = Color.Gray)
  }
}

@Composable
fun StatisticRow(label: String, value: String) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 6.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    Text(label, fontSize = 15.sp, color = Color.DarkGray)
    Text(value, fontSize = 15.sp, fontWeight = FontWeight.Medium)
  }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MaterialDatePickerDialog(
  initialDate: LocalDate,
  onDateSelected: (LocalDate) -> Unit,
  onDismiss: () -> Unit,
) {
  val context = LocalContext.current
  val calendar = Calendar.getInstance().apply {
    time = java.sql.Date.valueOf(initialDate.toString())
  }
  val year = calendar.get(Calendar.YEAR)
  val month = calendar.get(Calendar.MONTH)
  val day = calendar.get(Calendar.DAY_OF_MONTH)

  val datePickerDialog = android.app.DatePickerDialog(
    context,
    { _, selectedYear, selectedMonth, selectedDayOfMonth ->
      onDateSelected(LocalDate.of(selectedYear, selectedMonth + 1, selectedDayOfMonth))
    },
    year, month, day,
  )

  // Ensure the dialog is shown and dismissed correctly with Compose state
  DisposableEffect(Unit) {
    datePickerDialog.setOnDismissListener { onDismiss() }
    datePickerDialog.show()
    onDispose {
      datePickerDialog.dismiss()
    }
  }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AgeCalculatorScreenPreview() {
  AgeCalculatorScreen()
}
