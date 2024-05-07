package dev.vengateshm.compose_material3.custom_ui.navigation_drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.R

@Composable
fun CustomDrawer(
    modifier: Modifier = Modifier,
    selectedNavDrawerItem: NavDrawerItem,
    onNavDrawerItemClick: (NavDrawerItem) -> Unit,
    onCloseClick: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surface)
            .fillMaxHeight()
            .fillMaxWidth(fraction = 0.6f)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            IconButton(onClick = { onCloseClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Drawer Back Arrow Icon",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.astrology),
            contentDescription = "Logo Image"
        )
        Spacer(modifier = Modifier.height(16.dp))
        NavDrawerItem.entries.toTypedArray().take(2).forEach { navDrawerItem ->
            NavDrawerItemView(
                navDrawerItem = navDrawerItem,
                isSelected = navDrawerItem == selectedNavDrawerItem,
                onNavDrawerItemClick = {
                    onNavDrawerItemClick(navDrawerItem)
                })
            Spacer(modifier = Modifier.height(4.dp))
        }
        Spacer(modifier = Modifier.weight(1f))
        NavDrawerItem.entries.toTypedArray().takeLast(1).forEach { navDrawerItem ->
            NavDrawerItemView(
                navDrawerItem = navDrawerItem,
                isSelected = false,
                onNavDrawerItemClick = {
                    when (navDrawerItem) {
                        NavDrawerItem.Settings -> {
                            onNavDrawerItemClick(NavDrawerItem.Settings)
                        }

                        else -> {}
                    }
                })
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}