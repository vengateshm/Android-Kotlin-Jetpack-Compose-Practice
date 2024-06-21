package dev.vengateshm.compose_material3.ui_concepts.adaptive

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowWidthSizeClass
import dev.vengateshm.compose_material3.theme.JostRegular
import dev.vengateshm.compose_material3.theme.PlayWriteDeGrundRegular

data class NavItem(val title: String, val icon: ImageVector)

@Composable
fun NavigationSuiteSample() {
    var selectedItemIndex by remember {
        mutableIntStateOf(0)
    }
    val navItems = remember {
        listOf(
            NavItem("Home", Icons.Filled.Home),
            NavItem("Cart", Icons.Filled.ShoppingCart),
            NavItem("Profile", Icons.Filled.Person)
        )
    }

    val adaptiveInfo = currentWindowAdaptiveInfo()
    val customNavSuiteType = with(adaptiveInfo) {
        when (windowSizeClass.windowWidthSizeClass) {
            WindowWidthSizeClass.EXPANDED -> {
                NavigationSuiteType.NavigationRail
            }

            WindowWidthSizeClass.MEDIUM -> {
                NavigationSuiteType.NavigationBar
            }

            else -> {
                NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
            }
        }
    }

    NavigationSuiteScaffold(
        layoutType = customNavSuiteType,
        navigationSuiteItems = {
            navItems.forEachIndexed { index, navItem ->
                item(
                    selected = selectedItemIndex == index,
                    icon = { Icon(navItem.icon, contentDescription = null) },
                    label = {
                        Text(
                            text = navItem.title,
                            fontFamily = JostRegular
                        )
                    },
                    onClick = { selectedItemIndex = index }
                )
            }
        }) {
        when (selectedItemIndex) {
            0 -> Destination("🏛️Home")
            1 -> Destination("🛒Cart")
            2 -> Destination("👤Profile")
        }
    }
}

@Composable
fun Destination(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title, fontSize = 48.sp,
            fontFamily = PlayWriteDeGrundRegular
        )
    }
}

@Preview
@PreviewScreenSizes
@Composable
private fun NavigationSuiteSamplePreview() {
    MaterialTheme {
        NavigationSuiteSample()
    }
}