package dev.vengateshm.compose_material3.api_compose.state_holders

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Stable
interface SettingsScreenState {
    var showNotificationsSettings: Boolean
    var showThemeSwitch: Boolean
}

class SettingsScreenStateImpl(
    showNotificationsSettings: Boolean = false,
    showThemeSwitch: Boolean = true,
) : SettingsScreenState {
    override var showNotificationsSettings: Boolean by mutableStateOf(showNotificationsSettings)
    override var showThemeSwitch: Boolean by mutableStateOf(showThemeSwitch)
}

fun SettingsScreenState(): SettingsScreenState = SettingsScreenStateImpl()

@Composable
fun SettingsScreen(
    settingsScreenState: SettingsScreenState = remember { SettingsScreenState() }
) {

}