package dev.vengateshm.compose_material3.third_party_libraries.navigation_voyager

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun VoyagerNavigationSample(modifier: Modifier = Modifier) {
    Navigator(screen = VoyagerScreen1())
}