package dev.vengateshm.compose_material3.custom_ui.flight_block_ui

import dev.vengateshm.compose_material3.R
import java.io.Serializable
import java.util.Date
import kotlin.math.abs

const val EMPTY_STRING = ""
const val MINUTES_IN_AN_HOUR = 60
const val TIME_IN_MINUTES = 60000
const val MINUTES_IN_A_DAY = 1440
const val DAY_KEY_STRING = "D"
const val HOUR_KEY_STRING = "h"
const val MINUTE_KEY_STRING = "m"

object FlightBlockUtils {
    fun departureFlightStatus(data: FlightBlockData): Int {
        data.apply {
            val isEstimatedAndScheduledSame = estimatedDepartureDateTime == scheduledDepartureDateTime
            val departureStatus = when {
                //For Cancel status we are showing departureFlightStatus as "Scheduled"
                data.flightStatus == FlightStatus.CANCELED -> R.string.cmaterial3_scheduled_text
                actualDepartureDateTime.isNotEmpty() -> R.string.cmaterial3_actual_text
                isEstimatedAndScheduledSame -> R.string.cmaterial3_scheduled_text
                estimatedDepartureDateTime.isNotEmpty() -> R.string.cmaterial3_estimated_text
                else -> R.string.cmaterial3_scheduled_text
            }
            return departureStatus
        }
    }

    fun arrivalFlightStatus(data: FlightBlockData): Int {
        data.apply {
            val isEstimatedAndScheduledSame = estimatedArrivalDateTime == scheduledArrivalDateTime
            val arrivalStatus = when {
                data.flightStatus == FlightStatus.CANCELED -> R.string.cmaterial3_empty_text
                actualArrivalDateTime.isNotEmpty() -> R.string.cmaterial3_actual_text
                isEstimatedAndScheduledSame -> R.string.cmaterial3_scheduled_text
                estimatedArrivalDateTime.isNotEmpty() -> R.string.cmaterial3_estimated_text
                else -> R.string.cmaterial3_scheduled_text
            }
            return arrivalStatus
        }
    }

    fun getFlightProgressWithJourneyTotalTimeAndRemainingTime(
        departureTimeUTC: Date?, arrivalTimeUTC: Date?
    ): Triple<String, Int, String> {
        val currentTimeUTC = Date().time
        return if (departureTimeUTC != null && arrivalTimeUTC != null) {
            val departureTime = departureTimeUTC.time
            val arrivalTime = arrivalTimeUTC.time

            val totalTime = if (arrivalTime > departureTime) {
                abs(arrivalTime.minus(departureTime))
            } else {
                abs(departureTime.minus(arrivalTime))
            }

            val journeyTotalTimeInMinutes = totalTime.toInt() / TIME_IN_MINUTES
            val journeyTotalTime = getRemainingTimeLeftText(journeyTotalTimeInMinutes)

            val currentProgress = abs(currentTimeUTC - departureTime)
            val progressPercentage = (currentProgress * 100 / totalTime).toInt()
            val remainingTimeInMin = abs(arrivalTime - currentTimeUTC) / TIME_IN_MINUTES
            val remainingLeftTime = getRemainingTimeLeftText(remainingTimeInMin.plus(1).toInt())
            Triple(journeyTotalTime, progressPercentage, remainingLeftTime)
        } else Triple("", 0, "")
    }

    private fun getRemainingTimeLeftText(minutes: Int): String {
        if (minutes == 0) return EMPTY_STRING

        val hashMap = linkedMapOf<String, Int>()
        var result = EMPTY_STRING
        // Days left
        (minutes / MINUTES_IN_A_DAY).also {
            if (it != 0) hashMap[DAY_KEY_STRING] = it
        }
        // Hours left
        ((minutes % MINUTES_IN_A_DAY) / MINUTES_IN_AN_HOUR).also {
            if (it != 0) hashMap[HOUR_KEY_STRING] = it
        }
        // Minutes left
        ((minutes % MINUTES_IN_A_DAY) % MINUTES_IN_AN_HOUR).also {
            if (it != 0) hashMap[MINUTE_KEY_STRING] = it
        }

        hashMap.forEach { (s, i) ->
            if (result.isNotEmpty()) result += " "

            result += "$i$s"
        }

        return result
    }

    fun getDateTime(data: FlightBlockData, isDeparture: Boolean): String {
        data.apply {
            val (actualDateTime, scheduledDateTime, estimatedDateTime, isEstimatedAndScheduledSame) = when {
                isDeparture -> Tuple4(
                    actualDepartureDateTime,
                    scheduledDepartureDateTime,
                    estimatedDepartureDateTime,
                    estimatedDepartureDateTime == scheduledDepartureDateTime
                )
                else -> Tuple4(
                    actualArrivalDateTime,
                    scheduledArrivalDateTime,
                    estimatedArrivalDateTime,
                    estimatedArrivalDateTime == scheduledArrivalDateTime
                )
            }

            return when {
                data.flightStatus == FlightStatus.CANCELED -> scheduledDateTime
                actualDateTime.isNotEmpty() -> actualDateTime
                isEstimatedAndScheduledSame && scheduledDateTime.isNotEmpty() -> scheduledDateTime
                estimatedDateTime.isNotEmpty() -> estimatedDateTime
                else -> scheduledDateTime
            }
        }
    }
}

data class Tuple4<out A, out B, out C, out D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
) : Serializable {
    override fun toString(): String = "($first, $second, $third, $fourth)"
}