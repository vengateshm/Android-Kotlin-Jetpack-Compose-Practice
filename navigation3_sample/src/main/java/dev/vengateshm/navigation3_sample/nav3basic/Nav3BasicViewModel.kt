package dev.vengateshm.navigation3_sample.nav3basic

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dev.vengateshm.navigation3_sample.destinations.AppDestination

class Nav3BasicViewModel : ViewModel() {
  val backStack =
    mutableStateListOf<AppDestination>(AppDestination.HomeDestination) // Persist destination to retain during orientation
}