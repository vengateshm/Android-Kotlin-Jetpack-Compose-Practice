package dev.vengateshm.compose_material3.custom_ui.flight_block_ui.ui_components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import dev.vengateshm.appcore.utility.StringWrapper
import dev.vengateshm.appcore.utility.asStringWrapper
import dev.vengateshm.compose_material3.custom_ui.flight_block_ui.UiBadgeGreen
import dev.vengateshm.compose_material3.custom_ui.flight_block_ui.UiWhite

@Composable
fun FlightStatusBadge(
    modifier: Modifier = Modifier,
    flightStatusBadgeData: FlightStatusBadgeData,
) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = flightStatusBadgeData.backgroundColor),
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            text = flightStatusBadgeData.statusText.getFormattedString(context),
            color = flightStatusBadgeData.textColor,
        )
    }
}

data class FlightStatusBadgeData(
    val statusText: StringWrapper = StringWrapper.EMPTY,
    val backgroundColor: Color = UiBadgeGreen,
    val textColor: Color = UiWhite,
)

@Preview
@Composable
private fun FlightStatusBadgePreview(@PreviewParameter(FlightStatusBadgePreviewParameterProvider::class) flightStatusBadgeData: FlightStatusBadgeData) {
    FlightStatusBadge(
        flightStatusBadgeData = flightStatusBadgeData,
    )
}

class FlightStatusBadgePreviewParameterProvider : PreviewParameterProvider<FlightStatusBadgeData> {
    override val values: Sequence<FlightStatusBadgeData>
        get() = listOf(
            FlightStatusBadgeData(statusText = "On Time".asStringWrapper()),
            FlightStatusBadgeData(statusText = "Departed".asStringWrapper()),
            FlightStatusBadgeData(statusText = "In Flight".asStringWrapper()),
            FlightStatusBadgeData(statusText = "Arrived".asStringWrapper()),
            FlightStatusBadgeData(statusText = "Landed".asStringWrapper()),
            FlightStatusBadgeData(statusText = "Early".asStringWrapper()),
            FlightStatusBadgeData(statusText = "Delayed".asStringWrapper()),
            FlightStatusBadgeData(statusText = "Canceled".asStringWrapper()),
            FlightStatusBadgeData(statusText = "Return".asStringWrapper()),
        ).asSequence()
}