package dev.vengateshm.compose_material3.apps.age_calculator

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
class AgeCalculatorViewModel : ViewModel() {

  private val _title = mutableStateOf("Birthday")
  val title: State<String> = _title

  private val _fromDate = mutableStateOf<LocalDate?>(null) // Initialize to null or a default
  val fromDate: State<LocalDate?> = _fromDate

  private val _toDate = mutableStateOf(LocalDate.now()) // Default to today
  val toDate: State<LocalDate> = _toDate

  private val _calculatedAge = mutableStateOf(CalculatedAge())
  val calculatedAge: State<CalculatedAge> = _calculatedAge

  private val _ageStatistics = mutableStateOf(AgeStatistics())
  val ageStatistics: State<AgeStatistics> = _ageStatistics

  fun onTitleChange(newTitle: String) {
    _title.value = newTitle
  }

  fun onFromDateSelected(date: LocalDate) {
    _fromDate.value = date
    calculateAgeAndStatistics()
  }

  fun onToDateSelected(date: LocalDate) {
    _toDate.value = date
    calculateAgeAndStatistics()
  }

  private fun calculateAgeAndStatistics() {
    val startDate = _fromDate.value
    val endDate = _toDate.value

    if (startDate != null && startDate.isBefore(endDate.plusDays(1))) {
      val period = Period.between(startDate, endDate)
      _calculatedAge.value = CalculatedAge(
        years = period.years.toLong(),
        months = period.months.toLong(),
        days = period.days.toLong(),
      )

      val totalMonths = ChronoUnit.MONTHS.between(startDate, endDate)
      val totalYears =
        ChronoUnit.YEARS.between(startDate, endDate) // Can also use period.toTotalMonths() / 12
      val totalDays = ChronoUnit.DAYS.between(startDate, endDate)

      _ageStatistics.value = AgeStatistics(
        totalYears = totalYears,
        totalMonths = totalMonths,
        totalWeeks = totalDays / 7,
        totalDays = totalDays,
        totalHours = ChronoUnit.HOURS.between(startDate.atStartOfDay(), endDate.atStartOfDay()),
        totalMinutes = ChronoUnit.MINUTES.between(startDate.atStartOfDay(), endDate.atStartOfDay()),
        totalSeconds = ChronoUnit.SECONDS.between(startDate.atStartOfDay(), endDate.atStartOfDay()),
      )
    } else {
      _calculatedAge.value = CalculatedAge()
      _ageStatistics.value = AgeStatistics()
    }
  }
}