package dev.vengateshm.navigation3_sample.destinations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppDestination : NavKey {
  @Serializable
  data object WelcomeDestination : AppDestination, NavKey

  @Serializable
  data object ListDestination : AppDestination, NavKey

  @Serializable
  data class DetailDestination(val data: String) : AppDestination, NavKey

  @Serializable
  data object DetailDestination2 : AppDestination, NavKey

  @Serializable
  data object DetailDestination3 : AppDestination, NavKey

  @Serializable
  data class BottomSheetDestination(val data: String) : AppDestination, NavKey

  @Serializable
  data object MainDestination : AppDestination, NavKey

  @Serializable
  data object SettingsDestination : AppDestination, NavKey

  @Serializable
  data object NavigateWithResultScreen1 : AppDestination, NavKey

  @Serializable
  data object HomeDestination : AppDestination, NavKey, BottomNavItem {
    override val icon: ImageVector = Icons.Filled.Home
    override val title: String = "Home"
  }

  @Serializable
  data object ProfileDestination : AppDestination, NavKey, BottomNavItem {
    override val icon: ImageVector = Icons.Filled.AccountCircle
    override val title: String = "Profile"
  }
}