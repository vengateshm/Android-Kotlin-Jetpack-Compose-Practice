package dev.vengateshm.compose_material3.flight_block_ui

import java.util.Date

data class FlightBlockData(
    val actualDepartureDateTime: String = "",
    val actualArrivalDateTime: String = "",
    val estimatedDepartureDateTime: String = "",
    val estimatedArrivalDateTime: String = "",
    val scheduledDepartureDateTime: String = "",
    val scheduledArrivalDateTime: String = "",
    val departureCity: String,
    val arrivalCity: String,
    val departureStatus: String = "",
    val arrivalStatus: String = "",
    val journeyTime: String = "",
    val flightStatus: Enum<FlightStatus> = FlightStatus.NOT_AVAILABLE,
    val flightNumber: String = "",
    val operatedBy: String = "",
    val departureDateTimeUTC: Date? = null,
    val arrivalDateTimeUTC: Date? = null,
    val isBadgeVisible: Boolean = false,
    val departureTimeStyle: Boolean = false,
    val arrivalTimeStyle: Boolean = false,
    val isSegmentCancelledStatusStyle: Boolean = false,
    val noOfStops: Int = 0,
    val stopDescription: String = ""
)
