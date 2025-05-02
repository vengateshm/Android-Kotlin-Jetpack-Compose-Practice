package dev.vengateshm.compose_material3.logics.upcoming_trip

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.vengateshm.appcore.utility.StringWrapper
import dev.vengateshm.appcore.utility.asSingleString
import dev.vengateshm.compose_material3.utils.DEFAULT_TIME_FORMAT_UTC
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import dev.vengateshm.compose_material3.R

@RequiresApi(Build.VERSION_CODES.O)
class TripViewModel(
    private val walletReservationRepository: WalletReservationRepository,
    private val walletReservationSegmentRepository: WalletReservationSegmentRepository,
) : ViewModel() {

    private val _upcomingDateText = MutableLiveData<StringWrapper>()
    val upcomingDateText: LiveData<StringWrapper> = _upcomingDateText

    init {
        viewModelScope.launch {
            val upcomingDate = findEarliestFutureTripDate1()
            _upcomingDateText.value = getMonthsAndDays(upcomingDate)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun findEarliestFutureTripDate(): Date? {
        val now = Instant.now()
        val reservations = walletReservationRepository.getWalletReservations()
        val allSegments = mutableListOf<WalletReservationSegment>()
        for (reservation in reservations) {
            val segments = walletReservationSegmentRepository.getSegments(reservation.recordLocator)
            allSegments += segments
        }
        return allSegments
            .mapNotNull { it.scheduledDepartureDateTimeGMT?.toInstant() }
            .filter { it.isAfter(now) }
            .minOrNull()
            ?.let { Date.from(it) }
    }

    private suspend fun findEarliestFutureTripDate1(): Date? {
        val dateFormat = SimpleDateFormat(DEFAULT_TIME_FORMAT_UTC, Locale.US)
            .apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }
        var upcomingTripDate: Date? = null
        walletReservationRepository.getWalletReservations()
            .forEach { reservation ->
                walletReservationSegmentRepository.getSegments(reservation.recordLocator)
                    .forEach { segment ->
                        val currentDateTimeInUTC = dateFormat.parse(dateFormat.format(Date()))
                        if (currentDateTimeInUTC != null && segment.scheduledDepartureDateTimeGMT != null) {
                            val scheduledDepartureDateTimeInUTC =
                                dateFormat.parse(
                                    segment.scheduledDepartureDateTimeGMT.let {
                                        dateFormat.format(it)
                                    }.toString(),
                                )
                            if (currentDateTimeInUTC < scheduledDepartureDateTimeInUTC) {
                                if (upcomingTripDate == null) {
                                    upcomingTripDate = scheduledDepartureDateTimeInUTC
                                } else {
                                    if (upcomingTripDate!! > scheduledDepartureDateTimeInUTC) {
                                        upcomingTripDate = scheduledDepartureDateTimeInUTC
                                    }
                                }
                            }
                        }
                    }
            }
        return upcomingTripDate
    }

    private fun getMonthsAndDays(date: Date?): StringWrapper {
        date ?: return StringWrapper.EMPTY

        val today = LocalDate.now()
        val targetDate = date.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()

        if (targetDate?.isBefore(today) == true) return StringWrapper.EMPTY

        val period = Period.between(today, targetDate)
        val tempDate = today.plusMonths(period.toTotalMonths())
        val remainingDays = ChronoUnit.DAYS.between(tempDate, targetDate).toInt()
        val months = period.toTotalMonths().toInt()

        when {
            months > 1 && remainingDays > 1 -> {
                // %1$d months %2$d days
                return R.string.inactive_travel_mode_months_and_days.asSingleString(
                    months,
                    remainingDays,
                )
            }

            months > 1 && remainingDays == 1 -> {
                // %1$d months %2$d day
                return R.string.inactive_travel_mode_months_and_day.asSingleString(
                    months,
                    remainingDays,
                )
            }

            months == 1 && remainingDays > 1 -> {
                // %1$d month %2$d days
                return R.string.inactive_travel_mode_month_and_days.asSingleString(
                    months,
                    remainingDays,
                )
            }

            months == 1 && remainingDays == 1 -> {
                // %1$d month %2$d day
                return R.string.inactive_travel_mode_month_and_day.asSingleString(
                    months,
                    remainingDays,
                )
            }

            remainingDays > 1 -> {
                // %1$d days
                return R.string.inactive_travel_mode_days.asSingleString(remainingDays)
            }

            remainingDays == 1 -> {
                // %1$d day
                return R.string.inactive_travel_mode_day.asSingleString(remainingDays)
            }
        }
        return StringWrapper.EMPTY
    }
}

@RequiresApi(Build.VERSION_CODES.O)
class TripViewModelFactory(
    private val walletReservationRepository: WalletReservationRepository,
    private val walletReservationSegmentRepository: WalletReservationSegmentRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TripViewModel::class.java)) {
            return TripViewModel(
                walletReservationRepository,
                walletReservationSegmentRepository,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
