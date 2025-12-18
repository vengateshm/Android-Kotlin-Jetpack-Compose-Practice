package dev.vengateshm.navigation3_sample.destinations

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppDestination : NavKey {
  @Serializable
  data object Nav3List : NavKey

  @Serializable
  data class Nav3Detail(val data: String) : NavKey

  @Serializable
  data class BottomSheetDestination(val data: String) : NavKey
}