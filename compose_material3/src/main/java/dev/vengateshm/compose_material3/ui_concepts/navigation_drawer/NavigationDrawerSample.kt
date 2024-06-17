package dev.vengateshm.compose_material3.ui_concepts.navigation_drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.theme.InterBold
import dev.vengateshm.compose_material3.theme.InterRegular
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawerSample(modifier: Modifier = Modifier) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val drawerItems = listOf("Home", "Profile", "Settings")
    var selectedItem by remember { mutableStateOf(drawerItems[0]) }
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        scrimColor = Color.LightGray,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = CutCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
            ) {
                drawerItems.forEach { item ->
                    Text(
                        text = item,
                        modifier = Modifier
                            .clickable {
                                selectedItem = item
                                scope.launch {
                                    if (drawerState.isOpen) {
                                        drawerState.close()
                                    }
                                }
                            },
                        fontFamily = if (selectedItem == item) InterBold else InterRegular
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = selectedItem)
        }
    }
}