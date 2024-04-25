package dev.vengateshm.compose_material3.flight_block_ui

import dev.vengateshm.compose_material3.utils.DEFAULT_TIME_FORMAT_UTC
import dev.vengateshm.compose_material3.utils.getUTCTimeStampFromString
import java.util.Date

fun onTime() = FlightBlockData(
    flightNumber = "UA 1542",
    isBadgeVisible = true,
    flightStatus = FlightStatus.ON_TIME,
    estimatedDepartureDateTime = "03/25/2024 07:00 AM",
    estimatedArrivalDateTime = "03/25/2024 09:40 AM",
    scheduledDepartureDateTime = "03/25/2024 07:00 AM",
    scheduledArrivalDateTime = "03/25/2024 09:40 AM",
    departureCity = "IAH · Houston",
    arrivalCity = "ORD · Chicago",
    journeyTime = "2h 40m"
)

fun arrived() = FlightBlockData(
    flightNumber = "UA 1542",
    isBadgeVisible = true,
    flightStatus = FlightStatus.ARRIVED,
    departureStatus = "Actual",
    arrivalStatus = "Actual",
    actualDepartureDateTime = "04/12/2024 07:30 AM",
    actualArrivalDateTime = "04/12/2024 10:29 AM",
    estimatedDepartureDateTime = "04/12/2024 07:30 AM",
    estimatedArrivalDateTime = "04/12/2024 10:28 AM",
    scheduledDepartureDateTime = "04/12/2024 07:30 AM",
    scheduledArrivalDateTime = "04/12/2024 10:28 AM",
    departureCity = "ORD · Chicago",
    arrivalCity = "IAH · Houston",
)

// TODO Arrival time color should be green
fun early() = FlightBlockData(
    flightNumber = "UA 1542",
    isBadgeVisible = true,
    flightStatus = FlightStatus.EARLY,
    departureStatus = "Actual",
    arrivalStatus = "Actual",
    actualDepartureDateTime = "04/12/2024 07:30 AM",
    actualArrivalDateTime = "04/12/2024 10:15 AM",
    estimatedDepartureDateTime = "04/12/2024 07:30 AM",
    estimatedArrivalDateTime = "04/12/2024 10:28 AM",
    scheduledDepartureDateTime = "04/12/2024 07:30 AM",
    scheduledArrivalDateTime = "04/12/2024 10:28 AM",
    departureCity = "ORD · Chicago",
    arrivalCity = "IAH · Houston",
)

fun landed() = FlightBlockData(
    flightNumber = "UA 1542",
    isBadgeVisible = true,
    flightStatus = FlightStatus.LANDED,
    departureStatus = "Actual",
    arrivalStatus = "Actual",
    actualDepartureDateTime = "04/12/2024 07:30 AM",
    actualArrivalDateTime = "04/12/2024 10:29 AM",
    estimatedDepartureDateTime = "04/12/2024 07:30 AM",
    estimatedArrivalDateTime = "04/12/2024 10:28 AM",
    scheduledDepartureDateTime = "04/12/2024 07:30 AM",
    scheduledArrivalDateTime = "04/12/2024 10:28 AM",
    departureCity = "ORD · Chicago",
    arrivalCity = "IAH · Houston",
)

// TODO Show journey time left
fun departed() = FlightBlockData(
    flightNumber = "UA 1542",
    isBadgeVisible = true,
    flightStatus = FlightStatus.DEPARTED,
    departureStatus = "Actual",
    arrivalStatus = "Actual",
    actualDepartureDateTime = "04/12/2024 07:30 AM",
    actualArrivalDateTime = "04/12/2024 10:29 AM",
    estimatedDepartureDateTime = "04/12/2024 07:30 AM",
    estimatedArrivalDateTime = "04/12/2024 10:28 AM",
    scheduledDepartureDateTime = "04/12/2024 07:30 AM",
    scheduledArrivalDateTime = "04/12/2024 10:28 AM",
    departureCity = "ORD · Chicago",
    arrivalCity = "IAH · Houston",
    departureDateTimeUTC = Date("04/25/2024 11:20 AM".getUTCTimeStampFromString(DEFAULT_TIME_FORMAT_UTC)),
    arrivalDateTimeUTC = Date("04/25/2024 03:30 AM".getUTCTimeStampFromString(DEFAULT_TIME_FORMAT_UTC)),
)

fun inFlight() = FlightBlockData(
    flightNumber = "UA 1542",
    isBadgeVisible = true,
    flightStatus = FlightStatus.IN_FLIGHT,
    departureStatus = "Actual",
    arrivalStatus = "Actual",
    actualDepartureDateTime = "04/12/2024 07:30 AM",
    actualArrivalDateTime = "04/12/2024 10:29 AM",
    estimatedDepartureDateTime = "04/12/2024 07:30 AM",
    estimatedArrivalDateTime = "04/12/2024 10:28 AM",
    scheduledDepartureDateTime = "04/12/2024 07:30 AM",
    scheduledArrivalDateTime = "04/12/2024 10:28 AM",
    departureCity = "ORD · Chicago",
    arrivalCity = "IAH · Houston",
    departureDateTimeUTC = Date("04/25/2024 08:30 AM".getUTCTimeStampFromString(DEFAULT_TIME_FORMAT_UTC)),
    arrivalDateTimeUTC = Date("04/25/2024 03:30 PM".getUTCTimeStampFromString(DEFAULT_TIME_FORMAT_UTC)),
)

fun returned() = FlightBlockData(
    flightNumber = "UA 1542",
    isBadgeVisible = true,
    flightStatus = FlightStatus.RETURN,
    estimatedDepartureDateTime = "03/25/2024 09:00 AM",
    estimatedArrivalDateTime = "03/25/2024 10:33 AM",
    scheduledDepartureDateTime = "03/25/2024 09:00 AM",
    scheduledArrivalDateTime = "03/25/2024 10:33 AM",
    departureCity = "ORD · Chicago",
    arrivalCity = "MSP · Minneapolis",
    journeyTime = "1h 33m"
)

fun canceled() = FlightBlockData(
    flightNumber = "UA 1542",
    isBadgeVisible = true,
    flightStatus = FlightStatus.CANCELED,
    estimatedDepartureDateTime = "03/25/2024 08:00 AM",
    estimatedArrivalDateTime = "03/25/2024 09:39 AM",
    scheduledDepartureDateTime = "03/25/2024 08:00 AM",
    scheduledArrivalDateTime = "03/25/2024 09:39 AM",
    departureCity = "MSP · Minneapolis",
    arrivalCity = "ORD · Chicago",
    journeyTime = "1h 29m"
)

fun delayed() = FlightBlockData(
    flightNumber = "UA 1542",
    isBadgeVisible = true,
    flightStatus = FlightStatus.DELAYED,
    estimatedDepartureDateTime = "03/25/2024 07:10 AM",
    estimatedArrivalDateTime = "03/25/2024 09:48 AM",
    scheduledDepartureDateTime = "03/25/2024 07:00 AM",
    scheduledArrivalDateTime = "03/25/2024 09:40 AM",
    departureCity = "IAH · Houston",
    arrivalCity = "ORD · Chicago",
    journeyTime = "2h 38m"
)

val flightStatuses = listOf(
    onTime(),
    arrived(),
    early(),
    landed(),
    departed(),
    inFlight(),
    returned(),
    canceled(),
    delayed(),
)