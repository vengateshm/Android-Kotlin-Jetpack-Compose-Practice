package dev.vengateshm.compose_material3.custom_ui.flight_block_ui

import dev.vengateshm.appcore.utility.SingleString
import dev.vengateshm.appcore.utility.StringWrapper
import dev.vengateshm.compose_material3.custom_ui.flight_block_ui.ui_components.FlightStatusBadgeData

data class FlightBlockUiData(
    val flightNumber: String,
    val flightStatusBadgeData: FlightStatusBadgeData? = null,
    val departureStatus: StringWrapper,
    val arrivalStatus: StringWrapper,
    val departureTime: String,
    val arrivalTime: String,
    val departureDate: String,
    val arrivalDate: String,
    val journeyTimeLeft: SingleString? = null,
    val progress: Float,
    val shouldAnimateProgress: Boolean,
    val totalJourneyTime: String,
    val showLeftCheck: Boolean,
    val showRightCheck: Boolean,
    val showSolidTrack: Boolean,
    val showThumb: Boolean,
    val scheduledDepartureTime: String,
    val scheduledArrivalTime: String,
    val noOfStops: Int,
    val departureCity: String,
    val arrivalCity: String,
    val stopDescription: String,
    val operatedBy: String,
)