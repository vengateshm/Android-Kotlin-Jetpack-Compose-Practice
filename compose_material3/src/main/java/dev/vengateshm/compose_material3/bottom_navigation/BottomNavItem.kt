package dev.vengateshm.compose_material3.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val badgeCount: Int,
    val hasNews: Boolean
) {
    companion object {
        val bottomNavItems = listOf(
            BottomNavItem(
                title = "Home",
                icon = Icons.Outlined.Home,
                badgeCount = 0,
                hasNews = false
            ),
            BottomNavItem(
                title = "Posts",
                icon = Icons.Outlined.Category,
                badgeCount = 0,
                hasNews = false
            ),
            BottomNavItem(
                title = "Notifications",
                icon = Icons.Default.NotificationsNone,
                badgeCount = 3,
                hasNews = false
            ),
            BottomNavItem(
                title = "Profile",
                icon = Icons.Outlined.AccountCircle,
                badgeCount = 0,
                hasNews = true
            )
        )
    }
}
