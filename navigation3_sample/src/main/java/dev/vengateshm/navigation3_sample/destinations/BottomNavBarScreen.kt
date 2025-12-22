package dev.vengateshm.navigation3_sample.destinations

import androidx.compose.runtime.saveable.Saver
import androidx.navigation3.runtime.NavKey
import dev.vengateshm.navigation3_sample.R
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomNavBarScreen(
  val icon: Int,
  val title: String,
) : NavKey {
  @Serializable
  data object Home : BottomNavBarScreen(
    icon = R.drawable.outline_home_24,
    title = "Home",
  )

  @Serializable
  data object Me : BottomNavBarScreen(
    icon = R.drawable.outline_account_box_24,
    title = "Me",
  )
}

val bottomNavBarItems = listOf(
  BottomNavBarScreen.Home,
  BottomNavBarScreen.Me,
)

val BottomNavBarScreenSaver = Saver<BottomNavBarScreen, String>(
  save = {
    it::class.simpleName ?: "Unknown"
  },
  restore = {
    when (it) {
      BottomNavBarScreen.Home::class.simpleName -> BottomNavBarScreen.Home
      BottomNavBarScreen.Me::class.simpleName -> BottomNavBarScreen.Me
      else -> BottomNavBarScreen.Home
    }
  },
)