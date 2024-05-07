package dev.vengateshm.compose_material3.custom_ui.navigation_drawer

import dev.vengateshm.compose_material3.R

enum class NavDrawerItem(
    val title: String,
    val iconRes: Int
) {
    Home(
        title = "Home",
        iconRes = R.drawable.baseline_home_filled_24
    ),
    Profile(
        title = "Profile",
        iconRes = R.drawable.baseline_account_circle_24
    ),
    Settings(
        title = "Settings",
        iconRes = R.drawable.baseline_settings_24
    )
}
