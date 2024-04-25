package dev.vengateshm.compose_material3.flight_block_ui.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightBlockProgressBar(
    modifier: Modifier = Modifier,
    progress: Float = 0f,
    showThumb: Boolean = false,
    showSolidTrack: Boolean = false
) {
    Slider(
        modifier = modifier,
        value = progress,
        onValueChange = {

        },
        thumb = {
            if (showThumb) {
                Image(
                    painter = painterResource(
                        id = R.drawable.cmaterial3_flight_block_plane
                    ),
                    contentDescription = "Plane icon"
                )
            }
        },
        track = {
            Canvas(
                Modifier
                    .fillMaxWidth()
                    .height(if (showSolidTrack) 2.dp else 1.dp)
            ) {
                if (showSolidTrack) {
                    drawRoundRect(
                        color = Color(0XFF002244),
                    )
                } else {
                    drawLine(
                        color = Color(0XFF666666),
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )
                }
            }
        })
}

@Preview
@Composable
private fun FlightBlockProgressBarPreview() {
    FlightBlockProgressBar()
}