package dev.vengateshm.compose_material3.navigation_suite

/*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes

data class NavItem(val title: String, val icon: ImageVector)

@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
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

    NavigationSuiteScaffold(navigationSuiteItems = {
        navItems.forEachIndexed { index, navItem ->
            item(
                selected = selectedItemIndex == index,
                icon = { Icon(navItem.icon, contentDescription = null) },
                label = { Text(text = navItem.title) },
                onClick = { selectedItemIndex = index }
            )
        }
    })
}

@Preview
@PreviewScreenSizes
@Composable
private fun NavigationSuiteSamplePreview() {
    MaterialTheme {
        NavigationSuiteSample()
    }
}*/
