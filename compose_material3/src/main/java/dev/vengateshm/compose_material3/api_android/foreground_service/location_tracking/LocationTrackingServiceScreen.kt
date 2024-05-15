package dev.vengateshm.compose_material3.api_android.foreground_service.location_tracking

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun LocationTrackingServiceScreen(
    modifier: Modifier = Modifier,
    locationManager: LocationManager
) {
    var locationText by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = locationText)
        Button(onClick = {
            locationManager.getLocation { lat, long ->
                locationText = "Location: $lat, $long"
            }
        }) {
            Text(text = "Get Location")
        }
        Button(onClick = {
            Intent(context, LocationTrackingService::class.java)
                .also {
                    it.action = LocationTrackingAction.START.name
                    context.startService(it)
                }
        }) {
            Text(text = "Start Tracking")
        }
        Button(onClick = {
            Intent(context, LocationTrackingService::class.java)
                .also {
                    it.action = LocationTrackingAction.START.name
                    context.stopService(it)
                }
        }) {
            Text(text = "Stop Tracking")
        }
    }
}