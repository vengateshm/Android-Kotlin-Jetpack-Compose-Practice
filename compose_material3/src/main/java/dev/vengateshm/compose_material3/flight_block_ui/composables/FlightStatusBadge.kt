package dev.vengateshm.compose_material3.flight_block_ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.flight_block_ui.UiGreen
import dev.vengateshm.compose_material3.flight_block_ui.UiWhite

@Composable
fun FlightStatusBadge(
    modifier: Modifier = Modifier,
    data: FlightStatusBadgeData
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = data.backgroundColor)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            text = data.statusText,
            color = data.textColor
        )
    }
}

data class FlightStatusBadgeData(
    val statusText: String = "",
    val backgroundColor: Color = UiGreen,
    val textColor: Color = UiWhite
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FlightStatusBadgePreview() {
    FlightStatusBadge(
        data = FlightStatusBadgeData(statusText = "ON TIME")
    )
}