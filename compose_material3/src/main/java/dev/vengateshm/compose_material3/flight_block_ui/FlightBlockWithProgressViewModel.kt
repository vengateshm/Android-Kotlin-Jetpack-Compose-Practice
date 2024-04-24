package dev.vengateshm.compose_material3.flight_block_ui

import androidx.compose.ui.graphics.Color
import dev.vengateshm.compose_material3.R

class FlightBlockWithProgressViewModel {
    fun getFlightStatus(flightStatus: Enum<FlightStatus>): Triple<Int, Color, Color> {
        return when (flightStatus) {
            FlightStatus.ON_TIME -> Triple(
                R.string.cmaterial3_flight_status_ontime, UiBadgeGreen,
                UiWhite
            )

            FlightStatus.DEPARTED -> Triple(
                R.string.cmaterial3_flight_status_departed, UiBadgeGreen,
                UiWhite
            )

            FlightStatus.IN_FLIGHT -> Triple(
                R.string.cmaterial3_flight_status_inflight, UiBadgeGreen,
                UiWhite
            )

            FlightStatus.ARRIVED -> Triple(
                R.string.cmaterial3_flight_status_arrived, UiBadgeGreen,
                UiWhite
            )

            FlightStatus.LANDED -> Triple(
                R.string.cmaterial3_flight_status_landed, UiBadgeGreen,
                UiWhite
            )

            FlightStatus.DELAYED -> Triple(
                R.string.cmaterial3_flight_status_delayed,
                UiBadgeYellow,
                UiBlack
            )

            FlightStatus.CANCELED -> Triple(
                R.string.cmaterial3_flight_status_canceled,
                UiBadgeRed,
                UiWhite
            )

            FlightStatus.DIVERTED -> Triple(
                R.string.cmaterial3_flight_status_diverted,
                UiBadgeRed,
                UiWhite
            )

            FlightStatus.RETURN -> Triple(
                R.string.cmaterial3_flight_status_return,
                UiBadgeRed,
                UiWhite
            )

            FlightStatus.EARLY -> Triple(
                R.string.cmaterial3_flight_status_early,
                UiBadgeGreen,
                UiWhite
            )

            else -> Triple(
                R.string.cmaterial3_flight_status_not_available,
                UiBlack,
                UiWhite
            )
        }
    }
}