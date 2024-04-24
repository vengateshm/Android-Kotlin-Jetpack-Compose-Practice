package dev.vengateshm.compose_material3.flight_block_ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.R
import dev.vengateshm.compose_material3.flight_block_ui.FlightBlockData
import dev.vengateshm.compose_material3.flight_block_ui.FlightBlockUtils.arrivalFlightStatus
import dev.vengateshm.compose_material3.flight_block_ui.FlightBlockUtils.departureFlightStatus
import dev.vengateshm.compose_material3.flight_block_ui.FlightBlockUtils.getFlightProgressWithJourneyTotalTimeAndRemainingTime
import dev.vengateshm.compose_material3.flight_block_ui.FlightBlockWithProgressViewModel
import dev.vengateshm.compose_material3.flight_block_ui.FlightStatus
import dev.vengateshm.compose_material3.utils.DEFAULT_TIME_FORMAT_UTC
import dev.vengateshm.compose_material3.utils.HR_MIN_FORMAT
import dev.vengateshm.compose_material3.utils.MONTH_DAY_NO_YEAR
import dev.vengateshm.compose_material3.utils.getUTCTimeStampFromString
import dev.vengateshm.compose_material3.utils.parseDateTime
import java.util.Date

@Composable
fun FlightBlockWithProgress(
    modifier: Modifier = Modifier,
    data: FlightBlockData
) {
    val viewModel = remember {
        FlightBlockWithProgressViewModel()
    }
    val flightStatusBadgeData = remember {
        viewModel.getFlightStatus(data.flightStatus)
    }

    val departureDate = remember {
        (data.actualDepartureDateTime.takeIf { it.isNotEmpty() }
            ?: data.estimatedDepartureDateTime.takeIf { it.isNotEmpty() }
            ?: data.scheduledDepartureDateTime.takeIf { it.isNotEmpty() })?.let {
            parseDateTime(it, MONTH_DAY_NO_YEAR)
        } ?: ""
    }

    val arrivalDate = remember {
        (data.actualArrivalDateTime.takeIf { it.isNotEmpty() }
            ?: data.estimatedArrivalDateTime.takeIf { it.isNotEmpty() }
            ?: data.scheduledArrivalDateTime.takeIf { it.isNotEmpty() })?.let {
            parseDateTime(it, MONTH_DAY_NO_YEAR)
        } ?: ""
    }

    val departureStatus = remember {
        /*data.departureStatus.takeIf { it.isNotEmpty() }
            ?: departureFlightStatus(data)*/
        departureFlightStatus(data)
    }

    val arrivalStatus = remember {
        /*data.arrivalStatus.takeIf { it.isNotEmpty() }
            ?: arrivalFlightStatus(data)*/
        arrivalFlightStatus(data)
    }

    val context = LocalContext.current

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = data.flightNumber)
            if (data.isBadgeVisible) {
                FlightStatusBadge(
                    data = FlightStatusBadgeData(
                        statusText = context.resources.getString(flightStatusBadgeData.first),
                        backgroundColor = flightStatusBadgeData.second,
                        textColor = flightStatusBadgeData.third
                    )
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.cmaterial3_flight_block_circle),
                contentDescription = "Progress Left Image"
            )

            val flightProgressDataWithRemainingTime =
                getFlightProgressWithJourneyTotalTimeAndRemainingTime(
                    data.departureDateTimeUTC, data.arrivalDateTimeUTC
                )
            val flightJourneyCoveredPercentage = flightProgressDataWithRemainingTime.second
            val remainingTimeToLand = flightProgressDataWithRemainingTime.third
            val (journeyTimeLeft, progress, totalJourneyTime) = when {
                (data.flightStatus == FlightStatus.DEPARTED || data.flightStatus == FlightStatus.IN_FLIGHT) && (flightJourneyCoveredPercentage > 0 && flightJourneyCoveredPercentage in 1..100) -> {
                    Triple(
                        context.resources.getString(
                            R.string.cmaterial3_remainingtime_left_label,
                            remainingTimeToLand
                        ),
                        (flightJourneyCoveredPercentage / 100).toFloat(),
                        ""
                    )
                }

                data.flightStatus == FlightStatus.LANDED -> {
                    Triple("", 1f, "")
                }

                data.flightStatus == FlightStatus.ARRIVED || data.flightStatus == FlightStatus.EARLY -> {
                    Triple("", 1f, "")
                }

                else -> {
                    Triple("", null, data.journeyTime)
                }
            }

            Box(modifier = Modifier.weight(1f)) {
                FlightBlockProgressBar(progress = progress ?: 0f, showThumb = false)
                val showJourneyTime =
                    journeyTimeLeft.isEmpty() && progress == null && totalJourneyTime.isNotEmpty()
                if (showJourneyTime) {
                    Text(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(horizontal = 4.dp)
                            .align(Alignment.Center),
                        text = totalJourneyTime
                    )
                }
            }

            Image(
                painter = painterResource(id = R.drawable.cmaterial3_flight_block_circle),
                contentDescription = "Progress Right Image"
            )
        }

        if (data.noOfStops > 0) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pluralStringResource(
                        id = R.plurals.cmaterial3_no_of_stops,
                        data.noOfStops,
                        data.noOfStops
                    )
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = departureStatus))
            Text(text = stringResource(id = arrivalStatus))
        }

        val showScheduledDepartureTime =
            context.resources.getString(departureStatus) != context.resources.getString(R.string.cmaterial3_scheduled_text)
                    && context.resources.getString(departureStatus) != context.resources.getString(R.string.cmaterial3_canceled_text)
                    && data.flightStatus != FlightStatus.CANCELED

        val scheduledDepartureTime = if (showScheduledDepartureTime) {
            data.scheduledDepartureDateTime.takeIf { it.isNotEmpty() }?.let {
                parseDateTime(it, HR_MIN_FORMAT)
            } ?: ""
        } else ""

        val showScheduledArrivalTime =
            context.resources.getString(arrivalStatus) != context.resources.getString(R.string.cmaterial3_scheduled_text)
                    && data.flightStatus != FlightStatus.CANCELED

        val scheduledArrivalTime = if (showScheduledArrivalTime) {
            data.scheduledArrivalDateTime.takeIf { it.isNotEmpty() }?.let {
                parseDateTime(it, HR_MIN_FORMAT)
            } ?: ""
        } else ""

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            scheduledDepartureTime.takeIf { it.isNotEmpty() }?.let {
                Text(text = scheduledDepartureTime)
            }
            scheduledArrivalTime.takeIf { it.isNotEmpty() }?.let {
                Text(text = scheduledArrivalTime)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            departureDate.takeIf { it.isNotEmpty() }.run {
                Text(text = departureDate)
            }
            arrivalDate.takeIf { it.isNotEmpty() }.run {
                Text(text = departureDate)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            data.departureCity.takeIf { it.isNotEmpty() }.run {
                Text(text = data.departureCity)
            }
            data.arrivalCity.takeIf { it.isNotEmpty() }.run {
                Text(text = data.arrivalCity)
            }
        }

        data.stopDescription.takeIf { it.isNotEmpty() }.run {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = data.stopDescription)
            }
        }

        data.operatedBy.takeIf { it.isNotEmpty() }.run {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = data.operatedBy)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FlightBlockWithProgressPreview(@PreviewParameter(FlightBlockWithProgressPreviewParameterProvider::class) flightBlockData: FlightBlockData) {
    FlightBlockWithProgress(
        modifier = Modifier.padding(16.dp),
        data = flightBlockData
    )
}

class FlightBlockWithProgressPreviewParameterProvider : PreviewParameterProvider<FlightBlockData> {
    override val values: Sequence<FlightBlockData>
        get() = listOf(
            data1(),
            data2(),
            data3(),
            data4(),
            data5(),
            data6(),
            data7(),
            data8(),
            data9(),
        ).asSequence()

}

private fun data1() = FlightBlockData(
    flightStatus = FlightStatus.DEPARTED,
    departureStatus = "Actual",
    arrivalStatus = "Estimated",
    actualDepartureDateTime = "02/12/2024 12:48 PM",
    actualArrivalDateTime = "2/12/2024 3:41 PM",
    estimatedDepartureDateTime = "02/12/2024 01:05 PM",
    estimatedArrivalDateTime = "02/12/2024 03:41 PM",
    journeyTime = "1h 57m",
    scheduledDepartureDateTime = "02/12/2024 12:20 PM",
    scheduledArrivalDateTime = "02/12/2024 03:31 PM",
    departureCity = "BNA · Nashville",
    arrivalCity = "ORD · Chicago",
)

private fun data2() = FlightBlockData(
    flightStatus = FlightStatus.IN_FLIGHT,
    departureStatus = "Actual",
    arrivalStatus = "Estimated",
    actualDepartureDateTime = "02/12/2024 12:48 PM",
    actualArrivalDateTime = "2/12/2024 3:41 PM",
    estimatedDepartureDateTime = "02/12/2024 01:05 PM",
    estimatedArrivalDateTime = "02/12/2024 03:41 PM",
    departureCity = "BNA · Nashville",
    arrivalCity = "ORD · Chicago",
    departureDateTimeUTC = Date(
        "02/12/2024 06:20 PM".getUTCTimeStampFromString(
            DEFAULT_TIME_FORMAT_UTC
        )
    ),
    arrivalDateTimeUTC = Date(
        "02/12/2024 08:31 PM".getUTCTimeStampFromString(
            DEFAULT_TIME_FORMAT_UTC
        )
    )
)

private fun data3() = FlightBlockData(
    flightStatus = FlightStatus.LANDED,
    departureStatus = "Actual",
    arrivalStatus = "Actual",
    actualDepartureDateTime = "02/12/2024 12:48 PM",
    actualArrivalDateTime = "2/12/2024 3:41 PM",
    estimatedDepartureDateTime = "02/12/2024 01:05 PM",
    estimatedArrivalDateTime = "02/12/2024 03:41 PM",
    journeyTime = "14h 50m",
    departureCity = "BNA · Nashville",
    arrivalCity = "ORD · Chicago"
)

private fun data4() = FlightBlockData(
    flightStatus = FlightStatus.ARRIVED,
    departureStatus = "Actual",
    arrivalStatus = "Actual",
    actualDepartureDateTime = "02/12/2024 12:48 PM",
    actualArrivalDateTime = "2/12/2024 3:41 PM",
    estimatedDepartureDateTime = "02/12/2024 01:05 PM",
    estimatedArrivalDateTime = "02/12/2024 03:41 PM",
    departureCity = "BNA · Nashville",
    arrivalCity = "ORD · Chicago",
    departureDateTimeUTC = Date(
        "02/12/2024 06:20 PM".getUTCTimeStampFromString(
            DEFAULT_TIME_FORMAT_UTC
        )
    ),
    arrivalDateTimeUTC = Date(
        "02/12/2024 08:31 PM".getUTCTimeStampFromString(
            DEFAULT_TIME_FORMAT_UTC
        )
    )
)

private fun data5() = FlightBlockData(
    flightStatus = FlightStatus.CANCELED,
    departureStatus = "Canceled",
    actualDepartureDateTime = "02/12/2024 12:48 PM",
    actualArrivalDateTime = "2/12/2024 3:41 PM",
    estimatedDepartureDateTime = "02/12/2024 01:05 PM",
    estimatedArrivalDateTime = "02/12/2024 03:41 PM",
    scheduledDepartureDateTime = "02/12/2024 12:20 PM",
    scheduledArrivalDateTime = "02/12/2024 03:31 PM",
    departureCity = "BNA · Nashville",
    arrivalCity = "ORD · Chicago",
    departureDateTimeUTC = Date(
        "02/12/2024 06:20 PM".getUTCTimeStampFromString(
            DEFAULT_TIME_FORMAT_UTC
        )
    ),
    arrivalDateTimeUTC = Date(
        "02/12/2024 08:31 PM".getUTCTimeStampFromString(
            DEFAULT_TIME_FORMAT_UTC
        )
    ),
    isSegmentCancelledStatusStyle = true
)

private fun data6() = FlightBlockData(
    flightStatus = FlightStatus.ON_TIME,
    actualDepartureDateTime = "02/12/2024 12:48 PM",
    actualArrivalDateTime = "2/12/2024 3:41 PM",
    estimatedDepartureDateTime = "02/12/2024 01:05 PM",
    estimatedArrivalDateTime = "02/12/2024 03:41 PM",
    departureCity = "BNA · Nashville",
    arrivalCity = "ORD · Chicago",
    departureDateTimeUTC = Date(
        "02/12/2024 06:20 PM".getUTCTimeStampFromString(
            DEFAULT_TIME_FORMAT_UTC
        )
    ),
    arrivalDateTimeUTC = Date(
        "02/12/2024 08:31 PM".getUTCTimeStampFromString(
            DEFAULT_TIME_FORMAT_UTC
        )
    )
)

private fun data7() = FlightBlockData(
    departureStatus = "Actual",
    arrivalStatus = "Estimated",
    actualDepartureDateTime = "02/12/2024 12:48 PM",
    actualArrivalDateTime = "2/12/2024 3:41 PM",
    estimatedDepartureDateTime = "02/12/2024 01:05 PM",
    estimatedArrivalDateTime = "02/12/2024 03:41 PM",
    scheduledDepartureDateTime = "02/12/2024 12:20 PM",
    scheduledArrivalDateTime = "02/12/2024 03:31 PM",
    departureCity = "BNA · Nashville",
    arrivalCity = "ORD · Chicago"
)

//BoardingPass
private fun data8() = FlightBlockData(
    flightStatus = FlightStatus.ON_TIME,
    flightNumber = "UA 1793",
    actualDepartureDateTime = "02/12/2024 12:48 PM",
    actualArrivalDateTime = "2/12/2024 3:41 PM",
    estimatedDepartureDateTime = "02/12/2024 01:05 PM",
    estimatedArrivalDateTime = "02/12/2024 03:41 PM",
    scheduledDepartureDateTime = "02/12/2024 12:20 PM",
    scheduledArrivalDateTime = "02/12/2024 03:31 PM",
    departureCity = "BNA · Nashville",
    arrivalCity = "ORD · Chicago",
    operatedBy = "Operated by commuteair dba united express ",
    isBadgeVisible = true,
    departureDateTimeUTC = Date(
        "02/15/2024 05:20 AM".getUTCTimeStampFromString(
            DEFAULT_TIME_FORMAT_UTC
        )
    ),
    arrivalDateTimeUTC = Date(
        "02/15/2024 09:31 AM".getUTCTimeStampFromString(
            DEFAULT_TIME_FORMAT_UTC
        )
    ),
    departureTimeStyle = true,
    arrivalTimeStyle = true,
)

//IRROPS FOR BOARDING PASS
private fun data9() = FlightBlockData(
    flightStatus = FlightStatus.ON_TIME,
    flightNumber = "UA 1793",
    actualDepartureDateTime = "02/12/2024 12:48 PM",
    actualArrivalDateTime = "2/12/2024 3:41 PM",
    estimatedDepartureDateTime = "02/12/2024 01:05 PM",
    estimatedArrivalDateTime = "02/12/2024 03:41 PM",
    scheduledDepartureDateTime = "02/12/2024 12:20 PM",
    scheduledArrivalDateTime = "02/12/2024 03:31 PM",
    departureCity = "BNA · Nashville",
    arrivalCity = "ORD · Chicago",
    isBadgeVisible = true,
    departureDateTimeUTC = Date(
        "02/15/2024 05:20 AM".getUTCTimeStampFromString(
            DEFAULT_TIME_FORMAT_UTC
        )
    ),
    arrivalDateTimeUTC = Date(
        "02/15/2024 09:31 AM".getUTCTimeStampFromString(
            DEFAULT_TIME_FORMAT_UTC
        )
    ),
    departureTimeStyle = true,
    arrivalTimeStyle = true,
    noOfStops = 2,
    stopDescription = "Stop in Columbus, OH, US (CMH) -1H,23M"
)