package dev.vengateshm.compose_material3.custom_ui.speed_test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AdditionalInfo(
    modifier: Modifier = Modifier,
    ping: String, maxSpeed: String
) {

    @Composable
    fun RowScope.InfoItem(title: String, value: String) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = Color(0XFFAAAAAA)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        InfoItem(title = "PING", value = ping)
        VerticalDivider(thickness = 1.dp, color = Color.LightGray)
        InfoItem(title = "MAX SPEED", value = maxSpeed)
    }
}

@Preview(showBackground = true)
@Composable
private fun AdditionalInfoPreview() {
    AdditionalInfo(ping = "5 ms", maxSpeed = "150.0 Mbps")
}