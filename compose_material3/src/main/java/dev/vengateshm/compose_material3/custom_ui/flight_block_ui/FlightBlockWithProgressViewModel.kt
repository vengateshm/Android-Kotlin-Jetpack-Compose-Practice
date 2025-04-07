package dev.vengateshm.compose_material3.custom_ui.flight_block_ui

import androidx.compose.ui.graphics.Color
import dev.vengateshm.appcore.utility.SingleString
import dev.vengateshm.appcore.utility.asSingleString
import dev.vengateshm.compose_material3.R
import dev.vengateshm.compose_material3.custom_ui.flight_block_ui.ui_components.FlightStatusBadgeData
import dev.vengateshm.compose_material3.utils.HR_MIN_FORMAT
import dev.vengateshm.compose_material3.utils.MONTH_DAY_NO_YEAR
import dev.vengateshm.compose_material3.utils.parseDateTime

class FlightBlockWithProgressViewModel {

    fun getFlightBlockUiData(data: FlightBlockData): FlightBlockUiData {
        val departureStatus =
            /*data.departureStatus.takeIf { it.isNotEmpty() }
                ?: departureFlightStatus(data)*/
            FlightBlockUtils.departureFlightStatus(data)
        val arrivalStatus =
            /*data.arrivalStatus.takeIf { it.isNotEmpty() }
                ?: arrivalFlightStatus(data)*/
            FlightBlockUtils.arrivalFlightStatus(data)
        val departureTime =
            FlightBlockUtils.getDateTime(data, isDeparture = true).takeIf { it.isNotEmpty() }?.let {
                parseDateTime(it, HR_MIN_FORMAT)
            } ?: ""
        val arrivalTime =
            FlightBlockUtils.getDateTime(data, isDeparture = false).takeIf { it.isNotEmpty() }
                ?.let {
                    parseDateTime(it, HR_MIN_FORMAT)
                } ?: ""

        val departureDate =
            (data.actualDepartureDateTime.takeIf { it.isNotEmpty() }
                ?: data.estimatedDepartureDateTime.takeIf { it.isNotEmpty() }
                ?: data.scheduledDepartureDateTime.takeIf { it.isNotEmpty() })?.let {
                parseDateTime(it, MONTH_DAY_NO_YEAR)
            } ?: ""

        val arrivalDate =
            (data.actualArrivalDateTime.takeIf { it.isNotEmpty() }
                ?: data.estimatedArrivalDateTime.takeIf { it.isNotEmpty() }
                ?: data.scheduledArrivalDateTime.takeIf { it.isNotEmpty() })?.let {
                parseDateTime(it, MONTH_DAY_NO_YEAR)
            } ?: ""


        val (journeyTimeLeft, progress, totalJourneyTime) = getTimeLeftAndProgressAndTotalTime(data)

        val showScheduledDepartureTime =
            departureStatus != R.string.cmaterial3_scheduled_text
                    && departureStatus != R.string.cmaterial3_canceled_text
                    && data.flightStatus != FlightStatus.CANCELED

        val scheduledDepartureTime = if (showScheduledDepartureTime) {
            data.scheduledDepartureDateTime.takeIf { it.isNotEmpty() }?.let {
                parseDateTime(it, HR_MIN_FORMAT)
            } ?: ""
        } else ""

        val showScheduledArrivalTime =
            arrivalStatus != R.string.cmaterial3_scheduled_text
                    && data.flightStatus != FlightStatus.CANCELED

        val scheduledArrivalTime = if (showScheduledArrivalTime) {
            data.scheduledArrivalDateTime.takeIf { it.isNotEmpty() }?.let {
                parseDateTime(it, HR_MIN_FORMAT)
            } ?: ""
        } else ""

        val showLeftCheck =
            data.flightStatus == FlightStatus.ARRIVED || data.flightStatus == FlightStatus.DEPARTED
                    || data.flightStatus == FlightStatus.EARLY || data.flightStatus == FlightStatus.IN_FLIGHT
                    || data.flightStatus == FlightStatus.LANDED

        val showRightCheck =
            data.flightStatus == FlightStatus.ARRIVED || data.flightStatus == FlightStatus.EARLY

        val showThumb =
            data.flightStatus == FlightStatus.DEPARTED || data.flightStatus == FlightStatus.IN_FLIGHT
                    || data.flightStatus == FlightStatus.LANDED

        val showSolidTrack =
            data.flightStatus == FlightStatus.ARRIVED || data.flightStatus == FlightStatus.EARLY || data.flightStatus == FlightStatus.LANDED

        return FlightBlockUiData(
            flightNumber = data.flightNumber,
            flightStatusBadgeData = if (data.isBadgeVisible) getFlightStatusBadgeData(data) else null,
            departureStatus = departureStatus.asSingleString(),
            arrivalStatus = arrivalStatus.asSingleString(),
            departureTime = departureTime,
            arrivalTime = arrivalTime,
            departureDate = departureDate,
            arrivalDate = arrivalDate,
            journeyTimeLeft = journeyTimeLeft,
            progress = progress ?: 0f,
            shouldAnimateProgress = showThumb,
            totalJourneyTime = totalJourneyTime,
            showLeftCheck = showLeftCheck,
            showRightCheck = showRightCheck,
            showThumb = showThumb,
            scheduledDepartureTime = scheduledDepartureTime,
            scheduledArrivalTime = scheduledArrivalTime,
            showSolidTrack = showSolidTrack,
            noOfStops = data.noOfStops,
            departureCity = data.departureCity,
            arrivalCity = data.arrivalCity,
            stopDescription = data.stopDescription,
            operatedBy = data.operatedBy,
        )
    }

    private fun getTimeLeftAndProgressAndTotalTime(
        data: FlightBlockData,
    ): Triple<SingleString?, Float?, String> {
        val flightProgressDataWithRemainingTime =
            FlightBlockUtils.getFlightProgressWithJourneyTotalTimeAndRemainingTime(
                data.departureDateTimeUTC, data.arrivalDateTimeUTC,
            )
        val flightJourneyCoveredPercentage = flightProgressDataWithRemainingTime.second
        val remainingTimeToLand = flightProgressDataWithRemainingTime.third
        return when {
            (data.flightStatus == FlightStatus.DEPARTED || data.flightStatus == FlightStatus.IN_FLIGHT) && (flightJourneyCoveredPercentage > 0 && flightJourneyCoveredPercentage in 1..100) -> {
                Triple(
                    R.string.cmaterial3_remainingtime_left_label.asSingleString(remainingTimeToLand),
                    flightJourneyCoveredPercentage / 100f,
                    "",
                )
            }

            data.flightStatus == FlightStatus.LANDED -> {
                Triple(null, 1f, "")
            }

            data.flightStatus == FlightStatus.ARRIVED || data.flightStatus == FlightStatus.EARLY -> {
                Triple(null, 1f, "")
            }

            else -> {
                Triple(null, null, data.journeyTime)
            }
        }
    }

    private fun getFlightStatusBadgeData(data: FlightBlockData) =
        getFlightStatus(data.flightStatus).let {
            FlightStatusBadgeData(
                statusText = it.first.asSingleString(),
                backgroundColor = it.second,
                textColor = it.third,
            )
        }

    private fun getFlightStatus(flightStatus: Enum<FlightStatus>): Triple<Int, Color, Color> {
        return when (flightStatus) {
            FlightStatus.ON_TIME -> Triple(
                R.string.cmaterial3_flight_status_ontime, UiBadgeGreen,
                UiWhite,
            )

            FlightStatus.DEPARTED -> Triple(
                R.string.cmaterial3_flight_status_departed, UiBadgeGreen,
                UiWhite,
            )

            FlightStatus.IN_FLIGHT -> Triple(
                R.string.cmaterial3_flight_status_inflight, UiBadgeGreen,
                UiWhite,
            )

            FlightStatus.ARRIVED -> Triple(
                R.string.cmaterial3_flight_status_arrived, UiBadgeGreen,
                UiWhite,
            )

            FlightStatus.LANDED -> Triple(
                R.string.cmaterial3_flight_status_landed, UiBadgeGreen,
                UiWhite,
            )

            FlightStatus.DELAYED -> Triple(
                R.string.cmaterial3_flight_status_delayed,
                UiBadgeYellow,
                UiBlack,
            )

            FlightStatus.CANCELED -> Triple(
                R.string.cmaterial3_flight_status_canceled,
                UiBadgeRed,
                UiWhite,
            )

            FlightStatus.DIVERTED -> Triple(
                R.string.cmaterial3_flight_status_diverted,
                UiBadgeRed,
                UiWhite,
            )

            FlightStatus.RETURN -> Triple(
                R.string.cmaterial3_flight_status_return,
                UiBadgeRed,
                UiWhite,
            )

            FlightStatus.EARLY -> Triple(
                R.string.cmaterial3_flight_status_early,
                UiBadgeGreen,
                UiWhite,
            )

            else -> Triple(
                R.string.cmaterial3_flight_status_not_available,
                UiBlack,
                UiWhite,
            )
        }
    }
}