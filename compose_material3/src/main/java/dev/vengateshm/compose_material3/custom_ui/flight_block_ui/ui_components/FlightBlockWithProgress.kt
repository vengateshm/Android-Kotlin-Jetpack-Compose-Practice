package dev.vengateshm.compose_material3.custom_ui.flight_block_ui.ui_components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vengateshm.compose_material3.R
import dev.vengateshm.compose_material3.custom_ui.flight_block_ui.FlightBlockData
import dev.vengateshm.compose_material3.custom_ui.flight_block_ui.FlightBlockUiData
import dev.vengateshm.compose_material3.custom_ui.flight_block_ui.FlightBlockWithProgressViewModel
import dev.vengateshm.compose_material3.custom_ui.flight_block_ui.FlightStatus
import dev.vengateshm.compose_material3.custom_ui.flight_block_ui.flightStatuses
import dev.vengateshm.compose_material3.custom_ui.flight_block_ui.inFlight
import dev.vengateshm.compose_material3.api_android.media_store.MediaStoreUtil
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun FlightBlockWithProgressScreen(viewModel: FlightBlockWithProgressViewModel = FlightBlockWithProgressViewModel()) {
    val coroutineScope = rememberCoroutineScope()
    val graphicsLayer = rememberGraphicsLayer()

    val flightBlockUiDataList = flightStatuses
        .filter { it.flightStatus == FlightStatus.LANDED }
        .map { viewModel.getFlightBlockUiData(it) }

    val context = LocalContext.current

    Box(modifier = Modifier
        .fillMaxSize()
        .drawWithContent {
            graphicsLayer.record {
                this@drawWithContent.drawContent()
            }
            drawLayer(graphicsLayer)
        }
        .clickable {
            coroutineScope.launch {
                val bitmap = graphicsLayer.toImageBitmap().asAndroidBitmap()
                MediaStoreUtil(context).saveImage(bitmap)
            }
        }
        .background(color = Color.White)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(flightBlockUiDataList) { flightBlockUiData ->
                FlightBlockWithProgress(
                    modifier = Modifier.padding(16.dp),
                    flightBlockUiData = flightBlockUiData
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun FlightBlockWithProgress(
    modifier: Modifier = Modifier,
    flightBlockUiData: FlightBlockUiData
) {
    Column(modifier = modifier.fillMaxWidth()) {
        FlightNumberAndStatusBadge(flightBlockUiData)
        DepartureAndArrivalStatus(flightBlockUiData)
        TimeAndProgress(flightBlockUiData)
        StopOrRemainingTimeToLand(flightBlockUiData)
        ScheduledDepartureAndArrivalTime(flightBlockUiData)
        DepartureAndArrivalDate(flightBlockUiData)
        DepartureAndArrivalCity(flightBlockUiData)
        StopDescription(flightBlockUiData)
        OperatedBy(flightBlockUiData)
    }
}

@Composable
fun ScheduledDepartureAndArrivalTime(
    flightBlockUiData: FlightBlockUiData
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        flightBlockUiData.scheduledDepartureTime.takeIf { it.isNotEmpty() }?.let {
            Text(
                text = stringResource(
                    id = R.string.cmaterial3_scheduled_label,
                    it
                )
            )
        }
        flightBlockUiData.scheduledArrivalTime.takeIf { it.isNotEmpty() }?.let {
            Text(
                text = stringResource(
                    id = R.string.cmaterial3_scheduled_label,
                    it
                ),
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
private fun OperatedBy(flightBlockUiData: FlightBlockUiData) {
    flightBlockUiData.operatedBy.takeIf { it.isNotEmpty() }?.let { operatedBy ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = operatedBy)
        }
    }
}

@Composable
private fun StopDescription(flightBlockUiData: FlightBlockUiData) {
    flightBlockUiData.stopDescription.takeIf { it.isNotEmpty() }?.let { stopDescription ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stopDescription)
        }
    }
}

@Composable
private fun DepartureAndArrivalCity(flightBlockUiData: FlightBlockUiData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        flightBlockUiData.departureCity.takeIf { it.isNotEmpty() }?.let { departureCity ->
            Text(text = departureCity)
        }
        flightBlockUiData.arrivalCity.takeIf { it.isNotEmpty() }?.let { arrivalCity ->
            Text(text = arrivalCity)
        }
    }
}

@Composable
private fun DepartureAndArrivalDate(flightBlockUiData: FlightBlockUiData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        flightBlockUiData.departureDate.takeIf { it.isNotEmpty() }?.let { departureDate ->
            Text(text = departureDate)
        }
        flightBlockUiData.arrivalDate.takeIf { it.isNotEmpty() }?.let { arrivalDate ->
            Text(text = arrivalDate)
        }
    }
}

@Composable
private fun StopOrRemainingTimeToLand(flightBlockUiData: FlightBlockUiData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        flightBlockUiData.noOfStops.takeIf { it > 0 }
            ?.let { noOfStops ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = pluralStringResource(
                            id = R.plurals.cmaterial3_no_of_stops,
                            noOfStops,
                            noOfStops
                        )
                    )
                }
            }
        val context = LocalContext.current
        flightBlockUiData.journeyTimeLeft?.let { journeyTimeLeft ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = journeyTimeLeft.getFormattedString(context)
                )
            }
        }
    }
}

@Composable
private fun TimeAndProgress(flightBlockUiData: FlightBlockUiData) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        flightBlockUiData.departureTime.takeIf { it.isNotEmpty() }?.let { departureTime ->
            Text(
                text = departureTime,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        Box {
            Image(
                painter = painterResource(id = R.drawable.cmaterial3_flight_block_circle),
                contentDescription = "Progress Left Image"
            )
            if (flightBlockUiData.showLeftCheck) {
                Image(
                    painter = painterResource(id = R.drawable.cmaterial3_flight_block_check),
                    contentDescription = "Progress Left Image"
                )
            }
        }

        Box(modifier = Modifier.weight(1f)) {
            FlightBlockProgressBar(
                progress = flightBlockUiData.progress,
                showThumb = flightBlockUiData.showThumb,
                showSolidTrack = flightBlockUiData.showSolidTrack,
                shouldAnimate = flightBlockUiData.shouldAnimateProgress
            )

            flightBlockUiData.totalJourneyTime.takeIf { it.isNotEmpty() }?.let { totalJourneyTime ->
                Text(
                    modifier = Modifier
                        .background(color = Color.White)
                        .padding(horizontal = 8.dp)
                        .align(Alignment.Center),
                    text = totalJourneyTime
                )
            }
        }

        Box {
            Image(
                painter = painterResource(id = R.drawable.cmaterial3_flight_block_circle),
                contentDescription = "Progress Left Image"
            )
            if (flightBlockUiData.showRightCheck) {
                Image(
                    painter = painterResource(id = R.drawable.cmaterial3_flight_block_check),
                    contentDescription = "Progress Left Image"
                )
            }
        }

        flightBlockUiData.arrivalTime.takeIf { it.isNotEmpty() }?.let { arrivalTime ->
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = arrivalTime,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
private fun DepartureAndArrivalStatus(
    flightBlockUiData: FlightBlockUiData
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = flightBlockUiData.departureStatus.getFormattedString(context))
        Text(text = flightBlockUiData.arrivalStatus.getFormattedString(context))
    }
}

@Composable
private fun FlightNumberAndStatusBadge(flightBlockUiData: FlightBlockUiData) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = flightBlockUiData.flightNumber)
        flightBlockUiData.flightStatusBadgeData?.let { flightStatusBadgeData ->
            FlightStatusBadge(flightStatusBadgeData = flightStatusBadgeData)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FlightBlockWithProgressPreview(
    @PreviewParameter(
        FlightBlockWithProgressPreviewParameterProvider::class
    ) flightBlockUiData: FlightBlockUiData
) {
    FlightBlockWithProgress(
        modifier = Modifier.padding(16.dp),
        flightBlockUiData = flightBlockUiData
    )
}

class FlightBlockWithProgressPreviewParameterProvider : PreviewParameterProvider<FlightBlockData> {
    override val values: Sequence<FlightBlockData>
        get() = listOf(
//            onTime(),
//            departed(),
            inFlight(),
//            arrived(),
//            landed(),
//            delayed(),
//            canceled(),
//            returned(),
//            early(),
        ).asSequence()
}