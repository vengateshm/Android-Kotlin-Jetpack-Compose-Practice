package dev.vengateshm.android_kotlin_compose_practice.maps_spot_finder.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker

@Composable
fun MapScreen(viewModel: MapViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val scaffoldState = rememberScaffoldState()
    val uiSettings =
        remember {
            MapUiSettings(zoomControlsEnabled = false)
        }
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(MapEvent.ToggleFalloutMap)
                },
            ) {
                Icon(
                    imageVector = if (viewModel.state.isFalloutMap) Icons.Default.ToggleOff else Icons.Default.ToggleOn,
                    contentDescription = "Toggle Fallout map",
                )
            }
        },
        content = { padding ->
            GoogleMap(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(padding),
                properties = viewModel.state.properties,
                uiSettings = uiSettings,
                onMapLongClick = {
                    viewModel.onEvent(MapEvent.OnMapLongClick(it))
                },
            ) {
                viewModel.state.parkingSpots.forEach { spot ->
                    Marker(
                        position = LatLng(spot.lat, spot.lng),
                        title = "Parking spot (${spot.lat} ${spot.lng})",
                        snippet = "Long click to delete",
                        onInfoWindowLongClick = {
                            viewModel.onEvent(MapEvent.OnInfoWindowLongClick(spot))
                        },
                        onClick = {
                            it.showInfoWindow()
                            true
                        },
                        icon =
                            BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_VIOLET,
                            ),
                    )
                }
            }
        },
    )
}
